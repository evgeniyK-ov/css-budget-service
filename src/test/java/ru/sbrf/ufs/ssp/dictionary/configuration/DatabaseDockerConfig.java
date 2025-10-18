package ru.sbrf.ufs.ssp.dictionary.configuration;


import java.time.Duration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class DatabaseDockerConfig {

  @Bean(initMethod = "start", destroyMethod = "stop")
  public PostgreSQLContainer postgreSQLContainer() {
    return (PostgreSQLContainer) new PostgreSQLContainer(
        "postgres:11.1")
        .withDatabaseName("integration-tests-db")
        .withUsername("sa")
        .withPassword("sa")
        .withNetworkAliases("localhost")
        .withStartupTimeout(Duration.ofSeconds(720));
  }

}
