package ru.cpm.css.budget.core.api;

import java.util.Collections;
import java.util.List;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import ru.cpm.css.budget.core.mapper.AbstractEntityMapper;
import ru.cpm.css.budget.core.validation.AbstractValidation;

public interface AbstractCrudService<T, K, F, R, ENTITY> extends AbstractService<K, F, R, ENTITY> {

  default List<AbstractValidation<T, K>> getValidators() {
    return Collections.emptyList();
  }

  default Mono<T> validate(T body, @Nullable K entityId) {
    return AbstractValidation.validate(getValidators(), body, entityId);
  }

  AbstractEntityMapper<T, ENTITY> getMapperToEntity();

  default Mono<ENTITY> combineEntity(ENTITY entity) {
    return Mono.just(entity);
  }

  @Transactional
  default Mono<R> insert(T body) {
    return validate(body, null)
        .flatMap(el -> processInsertOperation(el))
        .flatMap(this::combineResponse);
  }


  @Transactional
  default Mono<R> update(K key, T body) {
    return validate(body, key)
        .flatMap(el -> processUpdateOperation(key, el))
        .flatMap(this::combineResponse);
  }


  @Transactional
  default Mono<Void> delete(K key) {
    return validate(null, key).then(Mono.defer(() -> getRepository().deleteById(key)));
  }

  private Mono<R> processUpdateOperation(K key, T validatedBody) {
    return getRepository().findById(key)
        .map(el -> getMapperToEntity().updateEntity(validatedBody, el))
        .flatMap(this::combineEntity)
        .flatMap(getRepository()::save)
        .map(getMapperToResult()::toDto);
  }

  private Mono<R> processInsertOperation(T validatedBody) {
    return combineEntity(getMapperToEntity().toEntity(validatedBody))
        .flatMap(el -> getRepository().save(el))
        .map(getMapperToResult()::toDto);
  }

}
