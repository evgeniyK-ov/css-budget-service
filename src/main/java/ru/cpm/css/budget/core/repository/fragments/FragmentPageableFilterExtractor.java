package ru.cpm.css.budget.core.repository.fragments;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FragmentPageableFilterExtractor<T, F> extends AbstractFragment {


  Flux<T> findAllByFilter(F filter, Pageable pageable);

  Mono<Long> countByFilter(F filter);


}
