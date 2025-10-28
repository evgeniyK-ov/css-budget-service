package ru.cpm.css.budget.service.repository.fragments.extractors;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.util.Pair;
import org.springframework.r2dbc.core.DatabaseClient.GenericExecuteSpec;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.cpm.common.css.model.dto.CompanyUnitFormFilter;
import ru.cpm.css.budget.core.model.entity.dictionary.CompanyUnitEntity.Fields;
import ru.cpm.css.budget.core.model.entity.dictionary.CompanyUnitFormEntity;
import ru.cpm.css.budget.core.repository.fragments.FragmentPageableFilterExtractor;
import ru.cpm.css.budget.core.utils.Constants;

@Repository
@RequiredArgsConstructor
public class CompanyUnitFormPageableFilterExtractor
    implements FragmentPageableFilterExtractor<CompanyUnitFormEntity, CompanyUnitFormFilter> {

  private static final Map<String, String> SORT_MAPPING = Map.of(
      Fields.ID, "id",
      Fields.DESCRIPTION, "description",
      Fields.NAME, "name",
      Fields.CODE, "code"
  );
  private static final String ORDER_BY = "ORDER BY %orderBy";
  private static final String LIMIT = """
      LIMIT  :limit
      OFFSET :offset
      """;
  private static final String SQL_SELECT = """
                            select *
                            from company_unit_form
      """;

  private static final String SQL_COUNT = """
                            select count(*)
                            from company_unit_form
      """;

  private static final String SQL_WHERE = """
                            where (:companyUnitId is null OR company_unit_id=:companyUnitId)
                            or (:filter is null OR code ILIKE CONCAT('%', :filter, '%'))
                            or (:filter is null OR name ILIKE CONCAT('%', :filter, '%'))
      """;


  private final R2dbcEntityTemplate entityTemplate;

  @Override
  public Flux<CompanyUnitFormEntity> findAllByFilter(CompanyUnitFormFilter filter,
      Pageable pageable) {

    var sortStr = pageable.getSort().stream()
        .map(el -> Pair.of(el, SORT_MAPPING.getOrDefault(el.getProperty(), Constants.EMPTY)))
        .filter(el -> !el.getSecond().equals(Constants.EMPTY))
        .map(el -> el.getSecond().concat(Constants.SPACE)
            .concat(el.getFirst().getDirection().toString()))
        .collect(
            Collectors.collectingAndThen(
                Collectors.joining(Constants.COMMA_SEPARATOR),
                el -> StringUtils.isNotEmpty(el) ? ORDER_BY.replace("%orderBy", el)
                    : Constants.EMPTY
            ));

    var specExecute = entityTemplate.getDatabaseClient()
        .sql(
            SQL_SELECT.concat(Constants.SPACE)
                .concat(SQL_WHERE)
                .concat(sortStr)
                .concat(Constants.SPACE)
                .concat(LIMIT)
        );
    specExecute = buildWhereSpec(specExecute, filter);

    return specExecute
        .bind("limit", pageable.getPageSize())
        .bind("offset", pageable.getOffset())
        .mapProperties(CompanyUnitFormEntity.class)
        .all();
  }

  @Override
  public Mono<Long> countByFilter(CompanyUnitFormFilter filter) {
    var specExecute = entityTemplate.getDatabaseClient()
        .sql(SQL_COUNT.concat(SQL_WHERE));
    specExecute = buildWhereSpec(specExecute, filter);

    return specExecute
        .map(el -> el.get(0, Long.class))
        .one();
  }

  private GenericExecuteSpec buildWhereSpec(GenericExecuteSpec specExecute,
      CompanyUnitFormFilter filter) {
    if (Objects.nonNull(filter) && Objects.nonNull(filter.getSearch())) {
      specExecute = specExecute.bind("filter", filter.getSearch());
    } else {
      specExecute = specExecute.bindNull("filter", String.class);
    }
    if (Objects.nonNull(filter) && Objects.nonNull(filter.getCompanyUnitId())) {
      specExecute = specExecute.bind("companyUnitId", filter.getCompanyUnitId());
    } else {
      specExecute = specExecute.bindNull("companyUnitId", UUID.class);
    }
    return specExecute;

  }
}
