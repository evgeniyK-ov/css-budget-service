package ru.cpm.css.budget.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ValidationException extends GeneralException {

  public ValidationException(String message) {
    super(message);
  }

}
