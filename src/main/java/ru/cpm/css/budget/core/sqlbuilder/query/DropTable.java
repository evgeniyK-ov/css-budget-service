package ru.cpm.css.budget.core.sqlbuilder.query;


import lombok.RequiredArgsConstructor;
import ru.cpm.css.budget.core.constant.Constants;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderDroppingTable;

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
        .replace(Constants.TEMPLATE_TABLE_FOR_REPLACING, tableName);
  }


}
