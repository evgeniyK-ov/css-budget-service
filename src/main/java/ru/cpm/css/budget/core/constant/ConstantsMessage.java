package ru.cpm.css.budget.core.constant;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstantsMessage {

  // === 1. Ошибки отсутствия сущностей (Not Found / Not Defined) ===
  public static final String TEMPLATE_NOT_FOUND_TYPE = "Не определен тип данных с кодом [ %s ]";
  public static final String TEMPLATE_NOT_FOUND_KEY_TYPE = "Не определен тип ключа с кодом [ %s ]";
  public static final String TEMPLATE_NOT_FOUND_TYPE_ID = "Не определен тип данных с идентификатором [ %s ]";
  public static final String TEMPLATE_NOT_FOUND_VERSION_ID = "Не определена версия справочника с идентификатором [ %s ]";
  public static final String TEMPLATE_NOT_FOUND_UK_ID = "Не определен ключ справочника с идентификатором [ %s ]";
  public static final String TEMPLATE_NOT_FOUND_VERSION = "Не определена версия с идентификатором [ %s ]";
  public static final String TEMPLATE_NOT_FOUND_ATTRIBUTE = "Не определен атрибут с кодом атрибута [ %s ] и справочник [ %s ]";
  public static final String TEMPLATE_NOT_FOUND_ATTRIBUTE_BY_ID = "Не найден атрибут с идентификатором [ %s ]";
  public static final String TEMPLATE_NOT_FOUND_ATTRIBUTE_BY_CODE = "Не найден атрибут с кодом [ %s ]";
  public static final String TEMPLATE_NOT_FOUND_DICTIONARY_ATTRIBUTE_BY_ID = "Не найден атрибут справочника с идентификатором [ %s ]";
  public static final String TEMPLATE_NOT_FOUND_ATTRIBUTE_IN_VERSION = "Не найден атрибут с идентификатором [ %s ] в версии справочника c идентификатором [ %s ]";
  public static final String TEMPLATE_NOT_FOUND_DICTIONARY = "Не определен справочник";
  public static final String TEMPLATE_NOT_FOUND_DICTIONARY_BY_CODE =
      TEMPLATE_NOT_FOUND_DICTIONARY + " с кодом [ %s ]";
  public static final String TEMPLATE_NOT_FOUND_DICTIONARY_BY_ID =
      TEMPLATE_NOT_FOUND_DICTIONARY + " с идентификатором [ %s ]";
  public static final String TEMPLATE_NOT_FOUND_CONTENT_ROW_DICTIONARY = "Не найдена запись в справочнике";
  public static final String TEMPLATE_NOT_ACTIVE_CONTENT_ROW_DICTIONARY = "Текущая запись не активна";

  // === 2. Ошибки дублирования и конфликта уникальности ===
  public static final String DUPLICATE_ATTRIBUTE_WITH_SYSTEM = "При попытке добавить системные атрибуты найдены дубликаты  [ %s ]";
  public static final String DUPLICATE_CODE_DICTIONARY_MSG = "При попытке добавить справочник найдены дубликаты по коду";
  public static final String DUPLICATE_CODE_DICTIONARY_TEMPLATE =
      DUPLICATE_CODE_DICTIONARY_MSG + " [ %s ]";
  public static final String DUPLICATE_ATTRIBUTE = "Атрибут с таким кодом уже существует";
  public static final String DUPLICATE_ATTRIBUTE_IN_VERSION_TEMPLATE =
      DUPLICATE_ATTRIBUTE + " в версии справочника";
  public static final String TEMPLATE_CONFLICT_ATTRIBUTES_UK_IN_VERSION = "Ключ с таким сочетанием атрибутов уже существует";
  public static final String TEMPLATE_CONFLICT_ATTRIBUTE_NAME_UK_IN_VERSION = "Ключ с именем [ %s ] уже существует в данной версии справочника";
  public static final String CONFLICT_FK_NOT_UNIQUE = "Внешний ключ на указанную колонку справочника уже существует";
  public static final String DUPLICATE_DATA_DICTIONARY_TEMPLATE = "При попытке добавить данные в справочник найдены дубликаты по полям уникального ключа [ %s ]";

  // === 3. Ошибки валидации данных и формата ===
  public static final String NOT_BLANK_MSG = "Поле не должно быть пустым";
  public static final String NOT_BLANK_MSG_TEMPLATE = "[ %s ]: " + NOT_BLANK_MSG;
  public static final String NOT_BLANK_IF_NOT_NULL_MSG = "Текст должен содержать хотя бы один символ, кроме пробелов";
  public static final String PATTERN_LAT_NUM_UNDERSCORE_MSG = "Должен содержать только латиницу, цифры и нижнее подчёркивание. Не может начинаться с цифр или _";
  public static final String INCOMPATIBLE_FIELDS = "Взаимоисключающие поля: [ %s ]";
  public static final String REQUIRED_FIELD_FOR_CREATE_ATTRIBUTE = "Требуются оба поля: code и name, когда attributeId не указан";
  public static final String BAD_ARGUMENT_DATE_TILL_DATE_FROM = "Дата начала должна быть раньше даты окончания";
  public static final String REQUIRED_ATTRIBUTE_VALUE = "Требуется заполнить значение атрибута [ %s ], если атрибут указан как обязательный";
  public static final String DATA_TYPE_AND_VALUE_INCOMPATIBLE = "Значение и тип данных атрибута несовместимы [ %s ] <> [ %s ]";
  public static final String DATA_TYPE_AND_DEFAULT_VALUE_INCOMPATIBLE = "Значение по умолчанию и тип данных атрибута несовместимы";
  public static final String CONFLICT_FROM_DATE = "Дата запрошенной дельты меньше текущей [ %s ] < [ %s ]";

  // === 4. Ошибки, связанные с публикацией и управлением версиями ===
  public static final String TEMPLATE_PUBLISH_PUBLISHED_VERSION = "Версия с идентификатором [ %s ] уже была опубликована";
  public static final String TEMPLATE_PUBLISH_NO_ATTRIBUTE_VERSION = "Версия с идентификатором [ %s ] не содержит атрибутов";
  public static final String FORBIDDEN_UPDATE_PUBLISHED_DICTIONARY = "Справочник опубликован. Изменение невозможно";
  public static final String ALREADY_EXISTING_VERSION_DRAFT = "Версия с таким номером уже существует";
  public static final String CONFLICT_UPDATE_DESCRIPTION_FOR_PUBLISHED_VERSION = "Версия опубликована. Описание нельзя изменить";
  public static final String CONFLICT_UPDATE_DATE_FOR_NOT_PUBLISHED_VERSION = "Версия в черновике. Даты действия версии нельзя изменить";
  public static final String FORBIDDEN_CHANGE_VERSION_FOR_DICT_ATTRIBUTE = "Запрещено менять версию справочника для созданного атрибута справочника";

  // === 5. Ошибки ссылочной целостности и внешних ключей (FK / Reference) ===
  public static final String CONFLICT_REF_VERSION_NOT_PUBLISHED = "Версия внешнего справочника не опубликована";
  public static final String CONFLICT_REF_VERSION_ATTR_CODE = "Не найден атрибут для ссылки на справочник";
  public static final String CONFLICT_PAIR_VERSION_ID_REF_VALUE_SOURCE = "Поля versionIdSource и valueSource должны быть или оба заполнены, или оба пусты";
  public static final String CONFLICT_FK_SELF_FK = "Создание внешнего ключа, ссылающегося на ту же запись, недопустимо";
  public static final String CONFLICT_DATA_TYPE_FK = "Ссылочный атрибут должен являться уникальным идентификатором";

  // === 6. Ошибки использования и зависимостей ===
  public static final String ATTRIBUTE_USED_IN_KEYS = "Атрибут используется в ключе версии справочника";
  public static final String CONFLICT_DATE_RANGE = "Некорректен выбранный диапазон";
  public static final String COLUMN_USED_IN_REF_PREFIX = "Колонки записи используются как ссылочные значения в других справочниках: ";
  public static final String COLUMN_USED_IN_REF_ITEM = "Справочник: [ %s ], Атрибуты: [ %s ]";

}
