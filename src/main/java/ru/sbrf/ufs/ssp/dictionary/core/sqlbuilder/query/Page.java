package ru.sbrf.ufs.ssp.dictionary.core.sqlbuilder.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class Page {

  private static final String TEMPLATE_PAGE = " OFFSET %pageSize * %pageNumber   FETCH NEXT %pageSize ROWS ONLY";

  private final Pageable pageable;


  @Override
  public String toString() {
    return TEMPLATE_PAGE
        .replace("%pageSize", String.valueOf(pageable.getPageSize()))
        .replace("%pageNumber", String.valueOf(pageable.getPageNumber()));
  }


}
