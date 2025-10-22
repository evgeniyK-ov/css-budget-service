package ru.cpm.css.budget.controller.dictionary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.cpm.common.css.model.dto.CompanyUnit;
import ru.cpm.common.css.model.dto.PageInfoDTO;
import ru.cpm.css.budget.core.api.AbstractApi;
import ru.cpm.css.budget.core.api.AbstractService;
import ru.cpm.css.budget.core.model.entity.dictionary.CompanyUnitEntity;


@RestController
@RequestMapping({"dictionary/companyUnit"})
@Tag(name = "Организационные единицы")
@RequiredArgsConstructor
public class CompanyUnitController implements
    AbstractApi<UUID, String, CompanyUnit, CompanyUnitEntity> {

  private final AbstractService<UUID, String, CompanyUnit, CompanyUnitEntity> service;

  @Override
  public AbstractService<UUID, String, CompanyUnit, CompanyUnitEntity> getService() {
    return service;
  }


  @GetMapping("/page")
  @ResponseBody
  @Operation(summary = "Получение списка")
  @Override
  public Mono<? extends PageInfoDTO<CompanyUnit>> getListByPage(
      @RequestParam(required = false, defaultValue = "") String filter,
      @ParameterObject @PageableDefault Pageable pageable) {
    return AbstractApi.super.getListByPage(filter, pageable);
  }

}
