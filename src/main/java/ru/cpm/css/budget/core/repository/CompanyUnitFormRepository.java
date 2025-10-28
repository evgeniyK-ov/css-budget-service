package ru.cpm.css.budget.core.repository;

import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.cpm.common.css.model.dto.CompanyUnitFormFilter;
import ru.cpm.css.budget.core.model.entity.dictionary.CompanyUnitEntity;
import ru.cpm.css.budget.core.model.entity.dictionary.CompanyUnitFormEntity;
import ru.cpm.css.budget.core.repository.fragments.FragmentHandler;
import ru.cpm.css.budget.core.repository.fragments.FragmentPageableFilterExtractor;
import ru.cpm.css.budget.service.repository.fragments.extractors.CompanyUnitFormPageableFilterExtractor;

@Repository
public interface CompanyUnitFormRepository extends AbstractRepository<CompanyUnitFormEntity, UUID, CompanyUnitFormFilter>,
    FragmentHandler<FragmentPageableFilterExtractor<CompanyUnitFormEntity, CompanyUnitFormFilter>> {


  @Override
  default Mono<Long> count(CompanyUnitFormFilter filter) {
    return getExtractor(CompanyUnitFormPageableFilterExtractor.class).countByFilter(filter);
  }

  @Override
  default Flux<CompanyUnitFormEntity> findAllBy(CompanyUnitFormFilter filter, Pageable pageable) {
    return getExtractor(CompanyUnitFormPageableFilterExtractor.class).findAllByFilter(filter, pageable);
  }
}
