package ru.cpm.css.budget.core.utils;

import static ru.cpm.css.budget.core.constant.Constants.TEMPLATE_DICTIONARY_CODE_FOR_REPLACING;
import static ru.cpm.css.budget.core.constant.Constants.TEMPLATE_PHYSICAL_TABLE_NAME;
import static ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FIELDS_FOR_REPLACING;
import static ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_FOR_REPLACING;
import static ru.cpm.css.budget.core.constant.Constants.TEMPLATE_TABLE_KEY_TYPE_FOR_REPLACING;
import static ru.cpm.css.budget.core.constant.Constants.TEMPLATE_VERSION_NUMBER_FOR_REPLACING;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.cpm.css.budget.core.model.KeyType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TableUtils {

  public static final Integer START_VERSION_NUMBER = 1;
  public static final String TEMPLATE_KEY_CODE = "%table_%fields_%keyType";

  public static String generatePhysicalTableName(String dictionaryCode, Integer versionNumber) {
    return TEMPLATE_PHYSICAL_TABLE_NAME
        .replace(TEMPLATE_DICTIONARY_CODE_FOR_REPLACING, dictionaryCode)
        .replace(TEMPLATE_VERSION_NUMBER_FOR_REPLACING, String.valueOf(versionNumber));
  }

  public static String generatePhysicalKeyName(String dictionaryCode,
      Integer versionNumber,
      List<String> fieldList, KeyType keyType) {
    var tableName = generatePhysicalTableName(dictionaryCode, versionNumber);
    var fields = fieldList.stream()
        .sorted()
        .map(String::toLowerCase)
        .collect(Collectors.joining(
            Constants.UNDERSCORE));
    return TEMPLATE_KEY_CODE
        .replace(TEMPLATE_TABLE_FOR_REPLACING, tableName)
        .replace(TEMPLATE_TABLE_FIELDS_FOR_REPLACING, fields)
        .replace(TEMPLATE_TABLE_KEY_TYPE_FOR_REPLACING, keyType.name().toLowerCase());

  }

}
