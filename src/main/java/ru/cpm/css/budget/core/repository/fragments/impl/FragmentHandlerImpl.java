package ru.cpm.css.budget.core.repository.fragments.impl;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.cpm.css.budget.core.exceptions.NotImplementedException;
import ru.cpm.css.budget.core.repository.fragments.AbstractFragment;
import ru.cpm.css.budget.core.repository.fragments.FragmentHandler;


@Repository
@RequiredArgsConstructor
public class FragmentHandlerImpl<E> implements FragmentHandler<E> {


  private final List<AbstractFragment> extractors;

  @Override
  public E getExtractor(Class<? extends AbstractFragment> cls) {

    return (E) extractors.stream()
        .filter(cls::isInstance)
        .findFirst()
        .orElseThrow(() -> new NotImplementedException(cls.getName()));
  }

}