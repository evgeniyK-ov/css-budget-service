package ru.cpm.css.budget.configurations;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiGroupConfig {


  @Bean
  public GroupedOpenApi apiUI() {
    return GroupedOpenApi.builder()
        .group("Интерфейсное Api")
        .pathsToExclude("/api/v1/external/**")
        .build();
  }

  @Bean
  public GroupedOpenApi apiExternal() {
    return GroupedOpenApi.builder()
        .group("Интеграционное Api")
        .pathsToMatch("/api/v1/external/**")
        .build();
  }

}
