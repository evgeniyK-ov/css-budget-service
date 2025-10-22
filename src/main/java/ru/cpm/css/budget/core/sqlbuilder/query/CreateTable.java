package ru.cpm.css.budget.core.sqlbuilder.query;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.cpm.css.budget.core.sqlbuilder.builder.impl.BuilderCreatingTable;
import ru.cpm.css.budget.core.sqlbuilder.constraint.AbstractConstraint;
import ru.cpm.css.budget.core.utils.Constants;
import ru.cpm.css.budget.core.sqlbuilder.index.AbstractIndex;

@RequiredArgsConstructor
public class CreateTable {

  private static final String TEMPLATE_CREATE = "CREATE TABLE  %table ( %fields )";
  private static final String TEMPLATE_FIELD_TYPE_CREATE = "%fieldName %type %default %nullable";
  private static final String TEMPLATE_COMMENT_CREATE = "comment on table %table is '%comment'";
  private static final String TEMPLATE_COMMENT_COLUMN_CREATE = "comment on COLUMN %table.%field   is '%comment'";
  private static final String TEMPLATE_DEFAULT_VALUE = "DEFAULT '%value'";

  private final String tableName;
  private final BuilderCreatingTable query;
  private List<FieldType> fields = new ArrayList<>();
  private List<AbstractConstraint> constraints = new ArrayList<>();
  private List<AbstractIndex> indexes = new ArrayList<>();

  private String comment;

  public BuilderCreatingTable query() {
    return query;
  }

  public CreateTable field(FieldType fieldType) {
    fields.add(fieldType);
    return this;
  }

  public CreateTable comment(String comment) {
    this.comment = comment;
    return this;
  }

  public CreateTable fields(List<FieldType> fields) {
    this.fields.addAll(fields);
    return this;
  }

  public CreateTable constraints(List<AbstractConstraint> constraints) {
    this.constraints.addAll(constraints);
    return this;
  }

  public CreateTable indexes(List<AbstractIndex> indexes) {
    this.indexes.addAll(indexes);
    return this;
  }


  @Override
  public String toString() {

    var contentField = fields.stream()
        .map(FieldType::toString)
        .collect(Collectors
            .collectingAndThen(
                Collectors.joining(Constants.COMMA_SEPARATOR),
                el -> {
                  var constraint = createTableConstraint();
                  if (StringUtils.isNotEmpty(constraint)) {
                    return el.concat(Constants.COMMA_SEPARATOR).concat(constraint);
                  }
                  return el;
                }
            ));
    return TEMPLATE_CREATE.replace(
            ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FIELDS_FOR_REPLACING, contentField)
        .replace(ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FOR_REPLACING, tableName)
        .concat(Constants.SEMICOLON_BLANK_SEPARATOR)
        .concat(createTableComment())
        .concat(createColumnComment())
        .concat(createTableIndex());
  }

  private String createTableComment() {
    return Optional.ofNullable(comment)
        .map(el ->
            TEMPLATE_COMMENT_CREATE
                .replace(ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FOR_REPLACING, tableName)
                .replace(
                    ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_COMMENT_FOR_REPLACING, comment)
                .concat(Constants.SEMICOLON_BLANK_SEPARATOR)
        ).orElse(Constants.EMPTY);
  }

  private String createTableIndex() {
    return Optional.ofNullable(indexes)
        .map(el ->
            el.stream().map(AbstractIndex::toString).collect(Collectors.collectingAndThen(
                        Collectors.joining(Constants.SEMICOLON_BLANK_SEPARATOR),
                        res -> res.concat(Constants.SEMICOLON_BLANK_SEPARATOR)
                    )
                )
                .concat(Constants.SEMICOLON_BLANK_SEPARATOR)
        ).orElse(Constants.EMPTY);
  }

  private String createColumnComment() {
    return fields.stream()
        .filter(el -> StringUtils.isNotEmpty(el.name))
        .filter(el -> StringUtils.isNotEmpty(el.comment))
        .map(el ->
            TEMPLATE_COMMENT_COLUMN_CREATE
                .replace(ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FOR_REPLACING, tableName)
                .replace(
                    ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FIELD_FOR_REPLACING, el.name)
                .replace(
                    ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_COMMENT_FOR_REPLACING, el.comment)
                .concat(Constants.SEMICOLON_BLANK_SEPARATOR)
        ).collect(Collectors.joining());
  }

  private String createTableConstraint() {
    return constraints.stream()
        .map(Object::toString)
        .collect(Collectors.joining(Constants.COMMA_SEPARATOR));
  }


  @Builder
  @AllArgsConstructor
  public static class FieldType {

    private static final String NULLABLE = "NULL";
    private static final String NOT_NULLABLE = "NOT NULL";
    private static final String PRIMARY_KEY = "PRIMARY KEY";


    private String name;
    private String type;
    private boolean isNullable;
    private String comment;
    private String defaultValue;


    @Override
    public String toString() {
      var defValue = Constants.EMPTY;
      if (StringUtils.isNotEmpty(defaultValue)) {
        defValue = TEMPLATE_DEFAULT_VALUE.replace("%value", this.defaultValue);
      }

      return TEMPLATE_FIELD_TYPE_CREATE
          .replace(
              ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FIELD_NAME_FOR_REPLACING, name)
          .replace(
              ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FIELD_TYPE_FOR_REPLACING, type)
          .replace(
              ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FIELD_DEFAULT_FOR_REPLACING, defValue)
          .replace(
              ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FIELD_NULLABLE_FOR_REPLACING,
              isNullable ? NULLABLE : NOT_NULLABLE);
    }
  }

}
