package ru.cpm.css.budget.core.sqlbuilder.builder.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ru.cpm.css.budget.core.sqlbuilder.builder.BuilderQuery;
import ru.cpm.css.budget.core.utils.Constants;
import ru.cpm.css.budget.core.sqlbuilder.query.DropTable;

@RequiredArgsConstructor
public class BuilderDroppingTable implements BuilderQuery {


  private DropTable dropTable;

  public DropTable dropTable(String tableName) {
    this.dropTable = new DropTable(tableName, this);
    return this.dropTable;
  }


  @Override
  public String build() {
    return Optional.ofNullable(this.dropTable).map(DropTable::toString).orElse(Constants.EMPTY)
        .concat(Constants.SPACE);
  }

}
