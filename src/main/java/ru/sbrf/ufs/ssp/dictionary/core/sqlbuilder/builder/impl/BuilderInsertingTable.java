package ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.builder.impl;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.builder.BuilderQuery;
import ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.query.Insert;
import ru.sbrf.ufs.ssp.dictionary.core.utils.Constants;

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
