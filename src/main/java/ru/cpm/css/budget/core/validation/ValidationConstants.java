package ru.cpm.css.budget.core.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationConstants {

  public static final String PATTERN_LAT_NUM_UNDERSCORE = "^[a-zA-Z][a-zA-Z0-9_]*$";

  public static final int SIZE_1 = 1;
  public static final int SIZE_16 = 16;
  public static final int SIZE_24 = 24;
  public static final int SIZE_32 = 32;
  public static final int SIZE_50 = 50;
  public static final int SIZE_255 = 255;
  public static final int SIZE_256 = 256;
  public static final int SIZE_1000 = 1000;
}