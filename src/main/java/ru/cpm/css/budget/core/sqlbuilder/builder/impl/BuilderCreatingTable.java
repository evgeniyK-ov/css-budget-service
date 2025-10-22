package ru.cpm.css.budget.core.sqlbuilder.builder.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ru.cpm.css.budget.core.sqlbuilder.builder.BuilderQuery;
import ru.cpm.css.budget.core.utils.Constants;
import ru.cpm.css.budget.core.sqlbuilder.query.CreateTable;

@RequiredArgsConstructor
public class BuilderCreatingTable implements BuilderQuery {


  private CreateTable createTable;

  public CreateTable createTable(String tableName) {
    this.createTable = new CreateTable(tableName, this);
    return this.createTable;
  }


  @Override
  public String build() {
    return Optional.ofNullable(this.createTable).map(CreateTable::toString).orElse(Constants.EMPTY)
        .concat(Constants.SPACE);
  }

}
