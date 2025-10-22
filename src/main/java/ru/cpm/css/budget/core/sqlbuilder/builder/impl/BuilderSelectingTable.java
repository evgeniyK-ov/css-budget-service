package ru.cpm.css.budget.core.sqlbuilder.builder.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ru.cpm.css.budget.core.sqlbuilder.builder.BuilderQuery;
import ru.cpm.css.budget.core.sqlbuilder.query.Select;
import ru.cpm.css.budget.core.utils.Constants;

@RequiredArgsConstructor
public class BuilderSelectingTable implements BuilderQuery {


  private Select select;

  public Select select() {
    return this.select;
  }

  public Select select(String tableName) {
    this.select = new Select(tableName, this);
    return this.select;
  }

  @Override
  public String build() {
    return Optional.ofNullable(this.select).map(Select::toString).orElse(Constants.EMPTY)
        .concat(Constants.SPACE);
  }

}
