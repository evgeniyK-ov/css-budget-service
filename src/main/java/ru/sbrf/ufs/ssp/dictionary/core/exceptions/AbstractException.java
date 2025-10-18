package ru.sbrf.ufs.ssp.dictionary.core.exceptions;




public abstract class AbstractException extends RuntimeException {
    protected AbstractException(Throwable throwable) {
        super(throwable);
    }

    protected AbstractException(String message) {
        super(message);
    }

    protected AbstractException(String template, String... parameters) {
        super(String.format(template, (Object[]) parameters));
    }

    protected AbstractException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
