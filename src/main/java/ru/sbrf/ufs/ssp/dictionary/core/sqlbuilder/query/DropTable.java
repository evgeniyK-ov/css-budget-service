package ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.query;


import static ru.sbrf.ufs.ssp.dictionary.core.constant.Constants.TEMPLATE_TABLE_FOR_REPLACING;

import lombok.RequiredArgsConstructor;
import ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.builder.impl.BuilderDroppingTable;

@RequiredArgsConstructor
public class DropTable {

  private static final String TEMPLATE_DROP = "DROP TABLE  %table CASCADE";

  private final String tableName;
  private final BuilderDroppingTable query;


  public BuilderDroppingTable query() {
    return query;
  }


  @Override
  public String toString() {

    return TEMPLATE_DROP
        .replace(TEMPLATE_TABLE_FOR_REPLACING, tableName);
  }


}
