package ru.cpm.css.budget.core.mapper;

import java.util.List;

public interface AbstractEntityMapper<DTO, ENTITY> {

  DTO toDto(ENTITY entity);

  ENTITY toEntity(DTO dto);

  List<ENTITY> toEntityList(List<DTO> dtoList);

  List<DTO> toDtoList(List<ENTITY> dtoList);

  ENTITY updateEntity(DTO dto, ENTITY entity);

}
