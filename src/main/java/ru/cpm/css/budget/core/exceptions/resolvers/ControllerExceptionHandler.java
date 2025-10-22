package ru.cpm.css.budget.core.exceptions.resolvers;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import ru.cpm.css.budget.core.exceptions.BaseError;
import ru.cpm.css.budget.core.utils.ExceptionUtils;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ControllerExceptionHandler {

  private static final MultiValueMap<String, String> HEADERS_MAP = new LinkedMultiValueMap<>();

  static {
    HEADERS_MAP.add("Content-Type", "application/problem+json");
  }


  @ExceptionHandler(Throwable.class)
  public ResponseEntity<BaseError> handleDefaultException(Throwable ex) {

    var errorBody = buildErrorDto(ex);

    log.error(ex.getClass().getSimpleName() + " : " + ex.getMessage(), ex);

    return new ResponseEntity<>(errorBody, HEADERS_MAP, annotatedOrDefault(ex.getClass()));
  }

  @ExceptionHandler(WebExchangeBindException.class)
  public ResponseEntity<BaseError> handleValidationException(Throwable ex) {

    var errorBody = buildErrorDto(ex);

    log.error(ex.getClass().getSimpleName() + " : " + ex.getMessage(), ex);

    return new ResponseEntity<>(errorBody, HEADERS_MAP, HttpStatus.BAD_REQUEST);
  }

  private BaseError buildErrorDto(Throwable ex) {
    return BaseError.builder()
        .title("TITLE_EXCEPTION")
        .type(ex.getClass().getSimpleName())
        .error(ex.getMessage())
        .details(Arrays.toString(ex.getStackTrace()))
        .details(ExceptionUtils.getMessageChain(ex))
        .build();
  }

  private HttpStatus annotatedOrDefault(Class<? extends Throwable> clazz) {
    var responseStatus = clazz.getAnnotation(ResponseStatus.class);
    if (responseStatus != null) {
      return responseStatus.value();
    }
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
