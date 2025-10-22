package ru.cpm.css.budget.core.repository;

import java.util.NoSuchElementException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface AbstractRepository<T, K, F> extends ReactiveCrudRepository<T, K> {


  default Mono<Long> count(F filter) {
    return Mono.error(new NoSuchElementException());
  }


  default Flux<T> findAllBy(F filter, Pageable pageable) {
    return Flux.error(new NoSuchElementException());
  }


}
