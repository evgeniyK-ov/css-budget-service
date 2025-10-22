package ru.cpm.css.budget.core.sqlbuilder.builder;


import ru.cpm.css.budget.core.utils.ExceptionUtils;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderCreatingTable;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderDroppingTable;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderInsertingTable;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderSelectingTable;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderUpdatingTable;

public interface BuilderQuery {


  static BuilderSelectingTable ofSelect() {
    return new BuilderSelectingTable();
  }

  static BuilderCreatingTable ofCreate() {
    return new BuilderCreatingTable();
  }

  static BuilderDroppingTable ofDrop() {
    return new BuilderDroppingTable();
  }

  static BuilderInsertingTable ofInsert() {
    return new BuilderInsertingTable();
  }

  static BuilderUpdatingTable ofUpdate() {
    return new BuilderUpdatingTable();
  }

  static <T extends BuilderQuery> T of(Class<T> cls) {
    return ExceptionUtils.exceptionWrapper(() -> cls.getConstructor(null).newInstance(),
        new RuntimeException());
  }

  String build();

}
