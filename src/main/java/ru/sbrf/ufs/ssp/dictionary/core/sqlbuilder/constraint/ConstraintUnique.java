package ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.constraint;

import static ru.sbrf.ufs.ssp.dictionary.core.constant.Constants.TEMPLATE_TABLE_FIELDS_FOR_REPLACING;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ru.sbrf.ufs.ssp.dictionary.core.utils.Constants;

@RequiredArgsConstructor
public class ConstraintUnique extends AbstractConstraint {

  private static final String TEMPLATE_CONSTRAINT = "CONSTRAINT %name UNIQUE(%fields)";

  protected final String name;
  protected final List<String> fields;

  @Override
  public String toString() {
    return TEMPLATE_CONSTRAINT
        .replace("%name", name)
        .replace(TEMPLATE_TABLE_FIELDS_FOR_REPLACING,
            fields.stream()
                .collect(Collectors.joining(Constants.COMMA_SEPARATOR))
        );
  }

}
