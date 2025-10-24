package ru.cpm.css.budget.service.dictionary;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.cpm.common.css.model.dto.CompanyUnit;
import ru.cpm.common.css.model.dto.PageInfoDTO;
import ru.cpm.css.budget.core.api.AbstractCrudService;
import ru.cpm.css.budget.core.mapper.AbstractEntityMapper;
import ru.cpm.css.budget.core.mapper.impl.AbstractCompanyUnitMapper;
import ru.cpm.css.budget.core.model.entity.dictionary.CompanyUnitEntity;
import ru.cpm.css.budget.core.repository.AbstractRepository;
import ru.cpm.css.budget.core.repository.CompanyUnitRepository;

@Service
@RequiredArgsConstructor
public class CompanyUnitService implements
    AbstractCrudService<CompanyUnit, UUID, String, CompanyUnit, CompanyUnitEntity> {

  private final CompanyUnitRepository companyUnitRepository;
  private final AbstractCompanyUnitMapper abstractCompanyUnitMapper;

  @Override
  public AbstractRepository<CompanyUnitEntity, UUID, String> getRepository() {
    return companyUnitRepository;
  }

  @Override
  public AbstractEntityMapper<CompanyUnit, CompanyUnitEntity> getMapperToEntity() {
    return abstractCompanyUnitMapper;
  }

  @Override
  public AbstractEntityMapper<CompanyUnit, CompanyUnitEntity> getMapperToResult() {
    return abstractCompanyUnitMapper;
  }

  @Override
  public Mono<? extends PageInfoDTO<CompanyUnit>> getListByPage(String filter, Pageable pageable) {
    return AbstractCrudService.super.getListByPage(filter, pageable);
  }

}
