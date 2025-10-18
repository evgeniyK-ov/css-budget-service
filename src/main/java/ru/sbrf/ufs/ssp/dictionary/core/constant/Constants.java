package ru.sbrf.ufs.ssp.dictionary.core.constant;

import java.time.LocalTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

  public static final UUID EMPTY_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
  public static final String TEMPLATE_TABLE_FOR_REPLACING = "%table";
  public static final String TEMPLATE_TABLE_FIELDS_FOR_REPLACING = "%fields";
  public static final String TEMPLATE_TABLE_FIELDS_VALUE_FOR_REPLACING = "%values";
  public static final String TEMPLATE_TABLE_FIELD_FOR_REPLACING = "%field";
  public static final String TEMPLATE_TABLE_COMMENT_FOR_REPLACING = "%comment";
  public static final String TEMPLATE_TABLE_FIELD_NAME_FOR_REPLACING = "%fieldName";
  public static final String TEMPLATE_TABLE_FIELD_TYPE_FOR_REPLACING = "%type";
  public static final String TEMPLATE_TABLE_KEY_TYPE_FOR_REPLACING = "%keyType";
  public static final String TEMPLATE_TABLE_FIELD_DEFAULT_FOR_REPLACING = "%default";
  public static final String TEMPLATE_TABLE_FIELD_NULLABLE_FOR_REPLACING = "%nullable";
  public static final String TEMPLATE_VERSION_NUMBER_FOR_REPLACING = "%versionNumber";
  public static final String TEMPLATE_DICTIONARY_CODE_FOR_REPLACING = "%code";
  public static final String TEMPLATE_PHYSICAL_TABLE_NAME = "data_"
      .concat(TEMPLATE_DICTIONARY_CODE_FOR_REPLACING)
      .concat("_v")
      .concat(TEMPLATE_VERSION_NUMBER_FOR_REPLACING);
  public static final int DEFAULT_SIZE_VARCHAR = 2048;
  public static final LocalTime END_DAY = LocalTime.of(23, 59, 59, 999_999_999);
  public static final String NOT_BLANK_IF_NOT_NULL_PATTERN = "^$|.*\\S.*";


}
