package ru.cpm.css.budget.core.sqlbuilder.query;


import static ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FIELDS_FOR_REPLACING;
import static ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FOR_REPLACING;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import ru.cpm.css.budget.core.utils.Constants;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderSelectingTable;

@RequiredArgsConstructor
public class Select {

  private static final String TEMPLATE_SELECT = "select %fields from %table";

  private final String tableName;
  private final BuilderSelectingTable query;
  private Where where;
  private Order order;
  private List<String> fields = new ArrayList<>();
  private Page page;

  public Select field(String fieldName) {
    fields.add(fieldName);
    return this;
  }

  public Select fields(List<String> fields) {
    this.fields.addAll(fields);
    return this;
  }

  public Select where(String where) {
    this.where = new Where(where);
    return this;
  }

  public Select and(String expression) {
    this.where = Optional.ofNullable(this.where)
        .map(el -> el.and(expression))
        .orElse(new Where().and(expression));
    return this;
  }

  public Select or(String expression) {
    this.where = Optional.ofNullable(this.where)
        .map(el -> el.or(expression))
        .orElse(new Where().or(expression));
    return this;
  }

  public Select where(Map<String, String> where) {
    this.where = new Where(where);
    return this;
  }

  public Select order(@Nullable Sort sort) {
    Optional.ofNullable(sort)
        .ifPresent(el -> this.order = new Order(sort));
    return this;
  }

  public Select page(@Nullable Pageable pageable) {
    Optional.ofNullable(pageable)
        .ifPresent(el -> this.page = new Page(pageable));
    return this;
  }

  public BuilderSelectingTable query() {
    return query;
  }


  @Override
  public String toString() {
    return TEMPLATE_SELECT.replace(TEMPLATE_TABLE_FIELDS_FOR_REPLACING,
            fields.stream().collect(Collectors.joining(Constants.COMMA_SEPARATOR)))
        .replace(TEMPLATE_TABLE_FOR_REPLACING, tableName)
        .concat(Constants.SPACE)
        .concat(Optional.ofNullable(this.where).map(Where::toString).orElse(Constants.EMPTY))
        .concat(Constants.SPACE)
        .concat(Optional.ofNullable(this.order).map(Order::toString).orElse(Constants.EMPTY))
        .concat(Optional.ofNullable(this.page).map(Page::toString).orElse(Constants.EMPTY));
  }
}
