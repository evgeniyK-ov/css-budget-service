package ru.cpm.css.budget.core.repository.fragments;

public interface FragmentHandler<E> {


  E getExtractor(Class<? extends AbstractFragment> cls);

}
