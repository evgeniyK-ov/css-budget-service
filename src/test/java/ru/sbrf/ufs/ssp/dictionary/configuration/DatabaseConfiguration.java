package ru.sbrf.ufs.ssp.dictionary.configuration;

import static io.r2dbc.spi.ConnectionFactoryOptions.DATABASE;
import static io.r2dbc.spi.ConnectionFactoryOptions.DRIVER;
import static io.r2dbc.spi.ConnectionFactoryOptions.HOST;
import static io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD;
import static io.r2dbc.spi.ConnectionFactoryOptions.PORT;
import static io.r2dbc.spi.ConnectionFactoryOptions.USER;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.sql.DataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class DatabaseConfiguration {

  @Bean
  @Primary
  public ConnectionFactory connectionFactory(PostgreSQLContainer<?> postgres) {
    return ConnectionFactories.get(ConnectionFactoryOptions.builder()
        .option(DRIVER, "postgresql")
        .option(HOST, postgres.getHost())
        .option(PORT, postgres.getFirstMappedPort())
        .option(USER, postgres.getUsername())
        .option(PASSWORD, postgres.getPassword())
        .option(DATABASE, postgres.getDatabaseName())
        .build());
  }

  @Bean
  @Primary
  public DataSource dataSource(JdbcDatabaseContainer<?> jdbcDatabaseContainer) {

    final String regex = "jdbc:postgresql:\\/\\/(.*):";
    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

    System.out.println("=====" + jdbcDatabaseContainer.getJdbcUrl());

    final Matcher matcher = pattern.matcher(jdbcDatabaseContainer.getJdbcUrl());
    matcher.find();
    var host = matcher.group(1);

    var dateSource = new SimpleDriverDataSource();
    dateSource.setUrl(jdbcDatabaseContainer.getJdbcUrl().replace(host, "localhost"));
    dateSource.setDriver(jdbcDatabaseContainer.getJdbcDriverInstance());
    dateSource.setUsername(jdbcDatabaseContainer.getUsername());
    dateSource.setPassword(jdbcDatabaseContainer.getPassword());
    return dateSource;
  }

  @Bean
  @Primary
  public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
    return new R2dbcTransactionManager(connectionFactory);
  }
}
