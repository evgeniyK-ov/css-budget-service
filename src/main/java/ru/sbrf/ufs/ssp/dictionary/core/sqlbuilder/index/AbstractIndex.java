package ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.index;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractIndex {

  public static IndexUnique ofUnique(String name, String table, @NonNull List<String> fieldName,
      @Nullable String where) {
    return new IndexUnique(name, table, fieldName, where);
  }


}
