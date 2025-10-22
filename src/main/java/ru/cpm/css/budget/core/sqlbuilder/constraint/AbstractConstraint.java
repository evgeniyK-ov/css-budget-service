package ru.cpm.css.budget.core.sqlbuilder.constraint;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractConstraint {


  public static ConstraintPrimary ofPrimary(String name, @NonNull List<String> fields) {
    return new ConstraintPrimary(name, fields);
  }

  public static ConstraintUnique ofUnique(String name, @NonNull List<String> fieldName) {
    return new ConstraintUnique(name, fieldName);
  }

  public static ConstraintForeign ofForeign(String name, @NonNull List<String> fieldName,
      @NonNull List<String> refFieldName,
      @NonNull String referenceTableName) {
    return new ConstraintForeign(name, fieldName, refFieldName, referenceTableName);
  }

}
