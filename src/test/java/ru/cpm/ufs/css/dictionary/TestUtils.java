package ru.cpm.ufs.css.dictionary;

import java.util.UUID;

public class TestUtils {

  public static String generateUniqueText(String prefix) {
    var unique = UUID.randomUUID().toString()
        .replace("-", "")
        .substring(0, 8);
    return prefix + "A" + unique;
  }
}