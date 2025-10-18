package ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.builder.impl;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.builder.BuilderQuery;
import ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.query.Update;
import ru.sbrf.ufs.ssp.dictionary.core.utils.Constants;

@RequiredArgsConstructor
public class BuilderUpdatingTable implements BuilderQuery {


  private Update update;


  public Update update(String tableName, Map<String, Object> fieldValues) {
    this.update = new Update(tableName, fieldValues, this);
    return this.update;
  }

  @Override
  public String build() {
    return Optional.ofNullable(this.update).map(Update::toString).orElse(Constants.EMPTY)
        .concat(Constants.SPACE);
  }

}
