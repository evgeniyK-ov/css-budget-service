package ru.cpm.css.budget.controller.dictionary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.cpm.common.css.model.dto.CompanyUnit;
import ru.cpm.common.css.model.dto.PageInfoDTO;
import ru.cpm.css.budget.core.api.AbstractCrudApi;
import ru.cpm.css.budget.core.api.AbstractCrudService;
import ru.cpm.css.budget.core.model.entity.dictionary.CompanyUnitEntity;


@RestController
@RequestMapping({"dictionary/companyUnit"})
@Tag(name = "Организационные единицы")
@RequiredArgsConstructor
public class CompanyUnitController implements
    AbstractCrudApi<CompanyUnit, UUID, String, CompanyUnit, CompanyUnitEntity> {

  private final AbstractCrudService<CompanyUnit, UUID, String, CompanyUnit, CompanyUnitEntity> service;

  @Override
  public AbstractCrudService<CompanyUnit, UUID, String, CompanyUnit, CompanyUnitEntity> getService() {
    return service;
  }


  @GetMapping("/page")
  @ResponseBody
  @Operation(summary = "Получение списка")
  @Override
  public Mono<? extends PageInfoDTO<CompanyUnit>> getListByPage(
      @RequestParam(required = false, defaultValue = "") String filter,
      @ParameterObject @PageableDefault Pageable pageable) {
    return AbstractCrudApi.super.getListByPage(filter, pageable);
  }

  @PostMapping
  @ResponseBody
  @Operation(summary = "Создать новую запись")
  @Override
  public Mono<CompanyUnit> insert(@RequestBody CompanyUnit companyUnit) {
    return AbstractCrudApi.super.insert(companyUnit);
  }

  @PutMapping("/{id}")
  @ResponseBody
  @Operation(summary = "Изменить запись")
  @Override
  public Mono<CompanyUnit> update(@PathVariable("id") UUID key,
      @RequestBody CompanyUnit companyUnit) {
    return AbstractCrudApi.super.update(key, companyUnit);
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  @Operation(summary = "Удалить новую запись")
  @Override
  public Mono<Void> delete(@PathVariable("id") UUID key) {
    return AbstractCrudApi.super.delete(key);
  }

}
