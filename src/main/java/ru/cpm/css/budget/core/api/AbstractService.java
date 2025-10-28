package ru.cpm.css.budget.core.api;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.cpm.common.css.model.dto.PageInfoDTO;
import ru.cpm.css.budget.core.exceptions.GeneralException;
import ru.cpm.css.budget.core.mapper.AbstractEntityMapper;
import ru.cpm.css.budget.core.repository.AbstractRepository;

public interface AbstractService<K, F, R, ENTITY> {

  AbstractRepository<ENTITY, K, F> getRepository();

  AbstractEntityMapper<R, ENTITY> getMapperToResult();

  default Mono<R> combineResponse(R response) {
    return Mono.just(response);
  }
  default Mono<ENTITY> combineEntityAfterSave(ENTITY entity) {
    return Mono.just(entity);
  }

  default Mono<ENTITY> combineEntityBeforeSave(ENTITY entity) {
    return Mono.just(entity);
  }

  default Mono<F> checkFilter(F filter) {
    return Mono.just(filter);
  }

  default Flux<R> getList(F filter) {
    throw new GeneralException("Not implementation method");
  }

  default Mono<R> get(K key) {
    throw new GeneralException("Not implementation method");
  }

  default Mono<? extends PageInfoDTO<R>> getListByPage(@NonNull F filter, Pageable pageable) {

    return
        checkFilter(filter)
            .then(
                getRepository().findAllBy(filter, pageable)
                    .concatMap(this::combineEntityAfterSave)
                    .map(getMapperToResult()::toDto)
                    .concatMap(this::combineResponse)
                    .collectList()
                    .zipWith(getRepository().count(filter))
                    .map(p -> PageInfoDTO.<R>builder()
                        .pageSize(pageable.getPageSize())
                        .pageNumber(pageable.getPageNumber())
                        .content(p.getT1())
                        .totalElements(p.getT2())
                        .build()
                    )
            );
  }


}
