package ru.cpm.css.budget.service.dictionary;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.cpm.common.css.model.dto.CompanyUnitForm;
import ru.cpm.common.css.model.dto.CompanyUnitFormFilter;
import ru.cpm.common.css.model.dto.PageInfoDTO;
import ru.cpm.css.budget.core.api.AbstractCrudService;
import ru.cpm.css.budget.core.mapper.AbstractCompanyUnitFormMapper;
import ru.cpm.css.budget.core.mapper.AbstractEntityMapper;
import ru.cpm.css.budget.core.model.entity.dictionary.CompanyUnitFormEntity;
import ru.cpm.css.budget.core.repository.AbstractRepository;
import ru.cpm.css.budget.core.repository.CompanyUnitFormRepository;
import ru.cpm.css.budget.core.repository.CompanyUnitRepository;

@Service
@RequiredArgsConstructor
public class CompanyUnitFormService implements
    AbstractCrudService<CompanyUnitForm, UUID, CompanyUnitFormFilter, CompanyUnitForm, CompanyUnitFormEntity> {

  private final CompanyUnitFormRepository companyUnitFormRepository;
  private final CompanyUnitRepository companyUnitRepository;
  private final AbstractCompanyUnitFormMapper mapper;

  @Override
  public AbstractRepository<CompanyUnitFormEntity, UUID, CompanyUnitFormFilter> getRepository() {
    return companyUnitFormRepository;
  }

  @Override
  public AbstractEntityMapper<CompanyUnitForm, CompanyUnitFormEntity> getMapperToEntity() {
    return mapper;
  }

  @Override
  public AbstractEntityMapper<CompanyUnitForm, CompanyUnitFormEntity> getMapperToResult() {
    return getMapperToEntity();
  }

  @Override
  public Mono<? extends PageInfoDTO<CompanyUnitForm>> getListByPage(CompanyUnitFormFilter filter,
      Pageable pageable) {
    return AbstractCrudService.super.getListByPage(filter, pageable);
  }

  @Override
  public Mono<CompanyUnitFormEntity> combineEntityBeforeSave(
      CompanyUnitFormEntity companyUnitFormEntity) {

    if (Objects.nonNull(companyUnitFormEntity.getCompanyUnit())) {
      return Optional.ofNullable(companyUnitFormEntity.getCompanyUnit().getId())
          .map(companyUnitId -> companyUnitRepository.findById(companyUnitId))
          .or(() ->
              Optional.ofNullable(companyUnitFormEntity.getCompanyUnit().getCode())
                  .map(code -> companyUnitRepository.findByCodeIgnoreCase(code))
          ).orElseGet(() -> Mono.empty())
          .map(el -> {
            companyUnitFormEntity.setCompanyUnitId(el.getId());
            return companyUnitFormEntity;
          });

    }
    return Mono.just(companyUnitFormEntity);
  }

  @Override
  public Mono<CompanyUnitFormEntity> combineEntityAfterSave(
      CompanyUnitFormEntity companyUnitFormEntity) {
    return companyUnitRepository.findById(companyUnitFormEntity.getCompanyUnitId())
        .map(el -> {
          companyUnitFormEntity.setCompanyUnit(el);
          return companyUnitFormEntity;
        });
  }

}
