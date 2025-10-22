package ru.cpm.css.budget.core.mapper.impl;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.cpm.common.css.model.dto.CompanyUnit;
import ru.cpm.css.budget.core.mapper.AbstractEntityMapper;
import ru.cpm.css.budget.core.model.entity.dictionary.CompanyUnitEntity;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    builder = @Builder(disableBuilder = true),
    uses = {}
)
public abstract class AbstractCompanyUnitMapper implements
    AbstractEntityMapper<CompanyUnit, CompanyUnitEntity> {

  @Override
  @Mapping(source = "id", target = "id", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  public abstract CompanyUnitEntity updateEntity(CompanyUnit companyUnit,
      @MappingTarget CompanyUnitEntity companyUnitEntity);


}
