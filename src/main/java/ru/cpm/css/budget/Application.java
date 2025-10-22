package ru.cpm.css.budget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ImportAutoConfiguration(value = {})
@EnableConfigurationProperties
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
