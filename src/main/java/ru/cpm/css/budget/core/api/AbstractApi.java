package ru.cpm.css.budget.core.api;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.cpm.common.css.model.dto.PageInfoDTO;

public interface AbstractApi<K, F, R, ENTITY> {

  AbstractService<K, F, R, ENTITY> getService();

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
  })
  default Flux<R> getList(F filter) {
    return getService().getList(filter);
  }

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
  })
  default Mono<? extends PageInfoDTO<R>> getListByPage(F filter, Pageable pageable) {
    return getService().getListByPage(filter, pageable);
  }

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
      @ApiResponse(responseCode = "404", description = "Не найден ресурс")
  })
  default Mono<R> get(K key) {
    return getService().get(key);
  }

}
