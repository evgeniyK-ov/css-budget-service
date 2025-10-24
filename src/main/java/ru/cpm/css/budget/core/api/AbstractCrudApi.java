package ru.cpm.css.budget.core.api;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Mono;

public interface AbstractCrudApi<T, K, F, R, ENTITY> extends AbstractApi<K, F, R, ENTITY> {


  @Override
  AbstractCrudService<T, K, F, R, ENTITY> getService();

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
  })
  default Mono<R> insert(T body) {

    return getService().insert(body);
  }

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
      @ApiResponse(responseCode = "404", description = "Данные для изменения не найдены"),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
  })
  default Mono<R> update(K key, T body) {

    return getService().update(key, body);
  }


  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
      @ApiResponse(responseCode = "404", description = "Данные для изменения не найдены"),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
  })
  default Mono<Void> delete(K key) {

    return getService().delete(key);
  }

}
