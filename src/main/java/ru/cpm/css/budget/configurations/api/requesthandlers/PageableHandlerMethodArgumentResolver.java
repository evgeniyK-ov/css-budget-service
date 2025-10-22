package ru.cpm.css.budget.configurations.api.requesthandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class PageableHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

  private static final String DEFAULT_PAGE = "0";
  private static final String DEFAULT_SIZE = "10";
  private static final Integer MAX_SIZE = 100;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return Pageable.class.equals(parameter.getParameterType());
  }

  @Override
  public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext,
      ServerWebExchange exchange) {
    String pageValue = exchange.getRequest().getQueryParams()
        .get("page")
        .stream().findFirst().orElse(DEFAULT_PAGE);
    String sizeValue = exchange.getRequest().getQueryParams()
        .get("size")
        .stream().findFirst().orElse(DEFAULT_SIZE);

    List<Sort.Order> sortList = new ArrayList<>();
    Optional.ofNullable(exchange.getRequest().getQueryParams().get("sort"))
        .ifPresent(sort -> {
          sort
              .stream()
              .forEach(el -> {
                String[] parts = el.split("[,:]");
                var direction =
                    parts.length == 2 ? Sort.Direction.fromString(parts[1].trim()) : Direction.ASC;
                var prop = parts[0];
                sortList.add(new Sort.Order(direction, prop));
              });

        });

    return Mono.just(
        PageRequest.of(
            Integer.parseInt(pageValue),
            Math.min(Integer.parseInt(sizeValue),
                MAX_SIZE), Sort.by(sortList)
        )
    );
  }
}
