package ru.cpm.css.budget.core.validation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.cpm.css.budget.core.exceptions.ValidationException;

@Component
public interface AbstractValidation<T, K> {

  Mono<ValidationResult<T>> validate(T obj, K key, ValidationResult<T> validationResult)
      throws ValidationException;

  default int ordered() {
    return Ordered.LOWEST_PRECEDENCE;
  }

  static <T, K> Mono<T> validate(List<AbstractValidation<T, K>> validations, @Nullable T body,
      K key) {

    if (validations == null || validations.isEmpty()) {
      return Mono.justOrEmpty(body);
    }

    return Flux.fromIterable(validations)
        .sort(Comparator.comparing(AbstractValidation::ordered))
        .flatMap(el -> {
          var validationResult = ValidationResult.<T>builder()
              .body(body)
              .errorList(new ArrayList<>()).build();
          return el.validate(body, key, validationResult);
        })
        .reduce(new ArrayList<String>(), (allErrors, result) -> {
          if (CollectionUtils.isNotEmpty(result.getErrorList())) {
            allErrors.addAll(result.getErrorList());
          }
          return allErrors;
        })
        .flatMap(errorList -> {
          if (errorList.isEmpty()) {
            return Mono.justOrEmpty(body);
          }
          return Mono.error(new ValidationException(errorList.toString()));
        })
        .cache();
  }


  @AllArgsConstructor
  @Getter
  @Builder
  class ValidationResult<T> {

    private T body;
    private List<String> errorList;
  }

}
