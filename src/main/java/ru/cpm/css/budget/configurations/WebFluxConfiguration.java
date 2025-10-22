package ru.cpm.css.budget.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;
import ru.cpm.css.budget.configurations.api.requesthandlers.PageableHandlerMethodArgumentResolver;

@Configuration
public class WebFluxConfiguration implements WebFluxConfigurer {

  @Override
  public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
    WebFluxConfigurer.super.configureArgumentResolvers(configurer);
    configurer.addCustomResolver(new PageableHandlerMethodArgumentResolver());
  }

  @Override
  public void addCorsMappings(CorsRegistry corsRegistry) {
    corsRegistry.addMapping("/**")
      .allowedOrigins("*")
      .allowedMethods("*")
      .allowedHeaders("*");
  }
}
