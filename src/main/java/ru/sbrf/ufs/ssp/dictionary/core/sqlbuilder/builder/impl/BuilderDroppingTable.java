package ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.builder.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.builder.BuilderQuery;
import ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.query.DropTable;
import ru.sbrf.ufs.ssp.dictionary.core.utils.Constants;

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
