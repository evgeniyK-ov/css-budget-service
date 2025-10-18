package ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.index;

import static ru.sbrf.ufs.ssp.dictionary.core.constant.Constants.TEMPLATE_TABLE_FIELDS_FOR_REPLACING;
import static ru.sbrf.ufs.ssp.dictionary.core.constant.Constants.TEMPLATE_TABLE_FOR_REPLACING;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ru.sbrf.ufs.ssp.dictionary.core.utils.Constants;

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
        .replace(TEMPLATE_TABLE_FOR_REPLACING, table)
        .replace(TEMPLATE_TABLE_FIELDS_FOR_REPLACING,
            fields.stream()
                .collect(Collectors.joining(Constants.COMMA_SEPARATOR))
        )
        .replace("%where", Optional.ofNullable(where).orElse(Constants.EMPTY));
  }

}
