package com.circe.invoice.configuration;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import java.util.HashMap;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {"com.circe.invoice.repository.data"},
    repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class,
    entityManagerFactoryRef = "entityManagerFactoryData",
    transactionManagerRef = "transactionManagerData")
public class DataSourceDataConfiguration {

  private final CirceConfiguration configuration;

  @Autowired
  public DataSourceDataConfiguration(CirceConfiguration configuration) {
    this.configuration = configuration;
  }

  @Bean(name = "dataSourceData")
  @ConfigurationProperties(prefix = "spring.datasource.data")
  public DataSource dataSource() {
    return DataSourceBuilder.create().driverClassName("org.postgresql.Driver").build();
  }

  @Bean(name = "entityManagerFactoryData")
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
      @Qualifier("dataSourceData") DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setDataSource(dataSource);
    factory.setPackagesToScan("com.circe.invoice.entity.data");
    factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    factory.setJpaPropertyMap(properties);
    return factory;
  }

  @Bean(name = "transactionManagerData")
  public PlatformTransactionManager transactionManager(
      @Qualifier("entityManagerFactoryData") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  @Bean("secondaryLiquibase")
  public SpringLiquibase secondaryLiquibase() {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setDataSource(dataSource());
    liquibase.setChangeLog("classpath:/db/changelog/data/db.data.changelog-master.xml");
    liquibase.setShouldRun(configuration.isLiquibaseDataEnabled());
    return liquibase;
  }
}
