package ru.sbrf.ufs.ssp.dictionary.core.utils;


import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.sbrf.ufs.ssp.dictionary.core.exceptions.GeneralException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtils {

  @FunctionalInterface
  public interface FallbackWithException<T> {

    T get(Throwable ex);
  }

  @FunctionalInterface
  public interface ExceptionPredicate<E extends Throwable> {

    boolean shouldSuppress(E exception);
  }

  public static String getMessageChain(Throwable ex) {
    return org.apache.commons.lang3.exception.ExceptionUtils
        .getThrowableList(ex).stream()
        .map(e -> {

          return e.getClass().getSimpleName() + ": " + e.getMessage();
        })
        .collect(Collectors.joining(" -> "));
  }



  public static <T, E extends Exception> T exceptionWrapper(Callable<T> supplier, Class<E> eClass)
      throws E {
    try {
      return supplier.call();
    } catch (Throwable ex) {
      throw exceptionWrapper(() -> eClass.getDeclaredConstructor(eClass).newInstance(eClass));
    }
  }

  public static <T, E extends Exception> T exceptionWrapper(Callable<T> supplier, E error)
      throws E {
    return exceptionWrapper(supplier, el -> Boolean.TRUE, error);
  }

  public static <T, E extends Exception> T exceptionWrapper(Callable<T> supplier,
      Predicate<Throwable> predicate, E error) throws E {
    try {
      return supplier.call();
    } catch (Throwable ex) {
      if (predicate.test(ex)) {
        throw error;
      } else {
        throw generateException(ex);
      }
    }
  }

  public static <T> T exceptionWrapper(Callable<T> supplier) {
    try {
      return supplier.call();
    } catch (Throwable ex) {
      throw generateException(ex);
    }
  }

  public static void exceptionWrapper(Runnable action, Consumer<Throwable> actionError) {
    try {
      action.run();
    } catch (Throwable x) {
      actionError.accept(x);
    }
  }

  private static GeneralException generateException(Throwable ex) {
    try {
      return (GeneralException) ex;
    } catch (Throwable e) {
      return new GeneralException(getMessageChain(ex), ex);
    }
  }

}
