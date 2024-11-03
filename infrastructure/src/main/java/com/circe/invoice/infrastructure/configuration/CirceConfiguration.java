package com.circe.invoice.infrastructure.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class CirceConfiguration {

  @Value("${liquibase.data.enabled}")
  private boolean isLiquibaseDataEnabled;

  @Value("${liquibase.referential.enabled}")
  private boolean isLiquibaseReferentialEnabled;
}
