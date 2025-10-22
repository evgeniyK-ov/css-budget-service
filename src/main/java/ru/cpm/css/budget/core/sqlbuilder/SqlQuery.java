package ru.cpm.css.budget.core.sqlbuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import ru.cpm.css.budget.core.sqlbuilder.builder.BuilderQuery;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderCreatingTable;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderDroppingTable;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderInsertingTable;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderSelectingTable;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderUpdatingTable;
import ru.cpm.css.budget.core.sqlbuilder.constraint.AbstractConstraint;
import ru.cpm.css.budget.core.sqlbuilder.query.CreateTable.FieldType;
import ru.cpm.css.budget.core.sqlbuilder.index.AbstractIndex;

public interface SqlQuery<T extends BuilderQuery, R> {

  R execute(T query);


  static BuilderCreatingTable buildCreateTableQuery(String tableName, String comment,
      List<FieldType> fields, List<AbstractConstraint> constraints, List<AbstractIndex> indexes) {
    return BuilderQuery.ofCreate()
        .createTable(tableName)
        .comment(comment)
        .fields(fields)
        .constraints(constraints)
        .indexes(indexes)
        .query();
  }

  static BuilderDroppingTable buildDroppingTableQuery(String tableName) {
    return BuilderQuery.ofDrop()
        .dropTable(tableName)
        .query();
  }

  static BuilderSelectingTable buildSelectQuery(String tableName, List<String> fields,
      Map<String, String> where,
      @Nullable Pageable pageable) {
    return BuilderQuery.ofSelect()
        .select(tableName)
        .fields(fields)
        .where(where)
        .order(Optional.ofNullable(pageable).map(Pageable::getSort).orElse(null))
        .page(pageable)
        .query();
  }

  static BuilderInsertingTable buildInsertQuery(String tableName, Map<String, Object> fieldValues) {
    return BuilderQuery.ofInsert()
        .insert(tableName, fieldValues)
        .query();
  }

  static BuilderUpdatingTable buildUpdateQuery(String tableName, Map<String, Object> fieldValues,
      Map<String, String> where) {
    return BuilderQuery.ofUpdate()
        .update(tableName, fieldValues)
        .where(where)
        .query();
  }

}
