package ru.cpm.css.budget.core.sqlbuilder.builder.impl;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ru.cpm.css.budget.core.sqlbuilder.builder.BuilderQuery;
import ru.cpm.css.budget.core.utils.Constants;
import ru.cpm.css.budget.core.sqlbuilder.query.Update;

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
