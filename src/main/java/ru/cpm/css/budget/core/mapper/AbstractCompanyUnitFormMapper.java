package ru.cpm.css.budget.core.mapper;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.cpm.common.css.model.dto.CompanyUnitForm;
import ru.cpm.css.budget.core.model.entity.dictionary.CompanyUnitFormEntity;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    builder = @Builder(disableBuilder = true),
    uses = {}
)
public abstract class AbstractCompanyUnitFormMapper implements
    AbstractEntityMapper<CompanyUnitForm, CompanyUnitFormEntity> {

  @Override
  @Mapping(source = "id", target = "id", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  public abstract CompanyUnitFormEntity updateEntity(CompanyUnitForm companyUnitForm,
      @MappingTarget CompanyUnitFormEntity companyUnitFormEntity);


}
