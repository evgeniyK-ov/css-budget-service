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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.cpm.common.css.model.dto.CompanyUnitForm;
import ru.cpm.common.css.model.dto.CompanyUnitFormFilter;
import ru.cpm.common.css.model.dto.PageInfoDTO;
import ru.cpm.css.budget.core.api.AbstractCrudApi;
import ru.cpm.css.budget.core.api.AbstractCrudService;
import ru.cpm.css.budget.core.model.entity.dictionary.CompanyUnitFormEntity;


@RestController
@RequestMapping({"dictionary/companyUnitForm"})
@Tag(name = "Формы ввода данных")
@RequiredArgsConstructor
public class CompanyUnitFormController implements
    AbstractCrudApi<CompanyUnitForm, UUID, CompanyUnitFormFilter, CompanyUnitForm, CompanyUnitFormEntity> {

  private final AbstractCrudService<CompanyUnitForm, UUID, CompanyUnitFormFilter, CompanyUnitForm, CompanyUnitFormEntity> service;

  @Override
  public AbstractCrudService<CompanyUnitForm, UUID, CompanyUnitFormFilter, CompanyUnitForm, CompanyUnitFormEntity> getService() {
    return service;
  }


  @GetMapping("/page")
  @ResponseBody
  @Operation(summary = "Получение списка")
  @Override
  public Mono<? extends PageInfoDTO<CompanyUnitForm>> getListByPage(
      @ParameterObject @ModelAttribute CompanyUnitFormFilter filter,
      @ParameterObject @PageableDefault Pageable pageable) {
    return AbstractCrudApi.super.getListByPage(filter, pageable);
  }

  @PostMapping
  @ResponseBody
  @Operation(summary = "Создать новую запись")
  @Override
  public Mono<CompanyUnitForm> insert(@RequestBody CompanyUnitForm companyUnit) {
    return AbstractCrudApi.super.insert(companyUnit);
  }

  @PutMapping("/{id}")
  @ResponseBody
  @Operation(summary = "Изменить запись")
  @Override
  public Mono<CompanyUnitForm> update(@PathVariable("id") UUID key,
      @RequestBody CompanyUnitForm companyUnitForm) {
    return AbstractCrudApi.super.update(key, companyUnitForm);
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  @Operation(summary = "Удалить новую запись")
  @Override
  public Mono<Void> delete(@PathVariable("id") UUID key) {
    return AbstractCrudApi.super.delete(key);
  }

}
