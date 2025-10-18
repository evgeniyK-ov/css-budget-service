package ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.query;

import java.util.Map;
import java.util.Optional;
import lombok.NoArgsConstructor;
import ru.sbrf.ufs.ssp.dictionary.core.utils.Constants;

@NoArgsConstructor
public class Where {

  private static final String TEMPLATE_WHERE = "where %where";
  private static final String TEMPLATE_AND = "and %and";
  private static final String TEMPLATE_OR = "or %or";

  private final StringBuilder whereSql = new StringBuilder();

  public Where(String whereSql) {
    this.whereSql.append(whereSql);
  }

  public Where(Map<String, String> mapWhereFields) {
    mapWhereFields
        .entrySet()
        .stream()
        .findFirst()
        .map(el -> {
              this.whereSql.append(Constants.SPACE.concat(el.getValue()));
              return this.whereSql;
            }
        )
        .map(el -> {
              mapWhereFields.entrySet().stream().skip(1)
                  .forEach(el1 -> el.append(
                          Constants.SPACE.concat(TEMPLATE_AND.replace("%and", el1.getValue()))
                      )
                  );
              return el;
            }
        );
  }

  public Where or(String expression) {
    if (this.whereSql.isEmpty()) {
      this.whereSql.append(expression);
    } else {
      this.whereSql.append(Constants.SPACE.concat(TEMPLATE_OR.replace("%or", expression)));
    }
    return this;
  }

  public Where and(String expression) {
    if (this.whereSql.isEmpty()) {
      this.whereSql.append(expression);
    } else {
      this.whereSql.append(Constants.SPACE.concat(TEMPLATE_AND.replace("%and", expression)));
    }
    return this;
  }

  @Override
  public String toString() {
    return Optional.ofNullable(whereSql)
        .filter(el -> !el.isEmpty())
        .map(el -> TEMPLATE_WHERE.replace("%where", el.toString()))
        .orElse(Constants.EMPTY);
  }

}
