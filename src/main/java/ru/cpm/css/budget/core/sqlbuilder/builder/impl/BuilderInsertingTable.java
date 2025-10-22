package ru.cpm.css.budget.core.sqlbuilder.builder.impl;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ru.cpm.css.budget.core.sqlbuilder.builder.BuilderQuery;
import ru.cpm.css.budget.core.utils.Constants;
import ru.cpm.css.budget.core.sqlbuilder.query.Insert;

@RequiredArgsConstructor

public class BuilderInsertingTable implements BuilderQuery {


  private Insert insert;


  public Insert insert(String tableName, Map<String, Object> fieldValues) {
    this.insert = new Insert(tableName, fieldValues, this);
    return this.insert;
  }

  @Override
  public String build() {
    return Optional.ofNullable(this.insert).map(Insert::toString).orElse(Constants.EMPTY)
        .concat(Constants.SPACE);
  }

}
