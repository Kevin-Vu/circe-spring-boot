package com.circe.invoice.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@EnableJpaRepositories(
        basePackages = {"com.circe.invoice.repository.data"},
        repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class,
        entityManagerFactoryRef = "entityManagerFactoryData",
        transactionManagerRef = "transactionManagerData"
)
public class DataSourceDataConfiguration {

    @Autowired
    private CirceConfiguration configuration;

    @Bean(name = "dataSourceData")
    @ConfigurationProperties(prefix = "spring.datasource.data")
    public DataSource dataSource() {
        return DataSourceBuilder.create().driverClassName("org.postgresql.Driver").build();
    }

    @Bean(name = "entityManagerFactoryData")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder, @Qualifier("dataSourceData") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.circe.invoice.entity.data")
                .build();
    }

    @Bean(name = "transactionManagerData")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactoryData")EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

    @ConfigurationProperties
    public LiquibaseProperties secondaryLiquibaseProperties(){
        return new LiquibaseProperties();
    }

    @Bean("secondaryLiquibase")
    public SpringLiquibase secondaryLiquibase(){
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("classpath:/db/changelog/data/db.data.changelog-master.xml");
        liquibase.setShouldRun(configuration.isLiquibaseDataEnabled());
        return liquibase;
    }
}
