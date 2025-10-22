package ru.cpm.css.budget.core.sqlbuilder.query;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import ru.cpm.css.budget.core.utils.Constants;

@RequiredArgsConstructor
public class Order {

  private static final String TEMPLATE_ORDER = "order by %order";

  private final Sort sort;

  @Override
  public String toString() {
    return sort.stream()
        .map(el -> el.getProperty().concat(Constants.SPACE).concat(el.getDirection().toString()))
        .collect(
            Collectors.collectingAndThen(Collectors.joining(),
                el -> el.isEmpty() ? Constants.EMPTY : TEMPLATE_ORDER.replace("%order", el)));

  }

}
