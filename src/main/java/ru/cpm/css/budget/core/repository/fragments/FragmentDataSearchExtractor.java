package ru.cpm.css.budget.core.repository.fragments;

import reactor.core.publisher.Flux;

public interface FragmentDataSearchExtractor<T, F> extends AbstractFragment {


 Flux<T> find(F filter);


}
