package ru.cpm.css.budget.core.sqlbuilder.index;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ru.cpm.css.budget.core.constant.Constants;

@RequiredArgsConstructor
public class IndexUnique extends AbstractIndex {

  private static final String TEMPLATE_INDEX = "CREATE UNIQUE INDEX %name on %table (%fields)  %where";
  private static final String TEMPLATE_WHERE = "where  %where";

  protected final String name;
  protected final String table;
  protected final List<String> fields;
  protected final String where;


  @Override
  public String toString() {
    return TEMPLATE_INDEX
        .replace("%name", name)
        .replace(Constants.TEMPLATE_TABLE_FOR_REPLACING, table)
        .replace(Constants.TEMPLATE_TABLE_FIELDS_FOR_REPLACING,
            fields.stream()
                .collect(Collectors.joining(ru.cpm.css.budget.core.utils.Constants.COMMA_SEPARATOR))
        )
        .replace("%where", Optional.ofNullable(where).orElse(
            ru.cpm.css.budget.core.utils.Constants.EMPTY));
  }

}
