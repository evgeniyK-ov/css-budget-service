package ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.builder.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.builder.BuilderQuery;
import ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.query.CreateTable;
import ru.sbrf.ufs.ssp.dictionary.core.utils.Constants;

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
