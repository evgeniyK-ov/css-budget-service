package ru.cpm.css.budget.core.sqlbuilder.query;


import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderInsertingTable;
import ru.cpm.css.budget.core.utils.Constants;

@RequiredArgsConstructor
public class Insert {

  private static final String TEMPLATE_CREATE = "INSERT INTO %table ( %fields ) VALUES (%values) RETURNING *";
  private static final String APOSTROPHE = "'";
  private static final String EMPTY_VALUE = "NULL";


  private final String tableName;
  private final Map<String, Object> fieldValues;
  private final BuilderInsertingTable query;


  public BuilderInsertingTable query() {
    return query;
  }

  public Insert fieldValues(Map<String, String> fieldValues) {
    fieldValues.putAll(fieldValues);
    return this;
  }


  @Override
  public String toString() {
    var fields = String.join(Constants.COMMA_SEPARATOR, fieldValues.keySet());
    var values = fieldValues.values().stream()
        .map(el ->
            Optional.ofNullable(el).map(Object::toString)
                .map(el1 -> APOSTROPHE.concat(el1).concat(APOSTROPHE))
                .orElse(EMPTY_VALUE)
        )
        .collect(Collectors.joining(Constants.COMMA_SEPARATOR));
    return TEMPLATE_CREATE.replace(
            ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FOR_REPLACING, tableName)
        .replace(ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FIELDS_FOR_REPLACING, fields)
        .replace(
            ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FIELDS_VALUE_FOR_REPLACING, values);
  }


}
