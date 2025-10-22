package ru.cpm.css.budget.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralException extends AbstractException {
    public GeneralException(Throwable throwable) {
        super(throwable);
    }

    public GeneralException(String message) {
        super(message);
    }

    public GeneralException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public GeneralException(String template, String... parameters) {
        this(String.format(template, (Object[]) parameters));
    }

}
