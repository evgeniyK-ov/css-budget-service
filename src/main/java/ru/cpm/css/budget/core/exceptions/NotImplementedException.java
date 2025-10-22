package ru.cpm.css.budget.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class NotImplementedException extends GeneralException {

  public NotImplementedException(String message) {
    super(message);
  }

  public NotImplementedException(String template, String... parameters) {
    this(String.format(template, (Object[]) parameters));
  }

  public NotImplementedException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
