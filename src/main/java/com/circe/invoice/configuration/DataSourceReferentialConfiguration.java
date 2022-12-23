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
import org.springframework.context.annotation.Primary;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@EnableJpaRepositories(
        basePackages = {"com.circe.invoice.repository.referential"},
        repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class,
        entityManagerFactoryRef = "entityManagerFactoryReferential",
        transactionManagerRef = "transactionManagerReferential"
)
public class DataSourceReferentialConfiguration {

    @Autowired
    private CirceConfiguration configuration;

    @Primary
    @Bean(name = "dataSourceReferential")
    @ConfigurationProperties(prefix = "spring.datasource.referential")
    public DataSource dataSource() {
        return DataSourceBuilder.create().driverClassName("org.postgresql.Driver").build();
    }

    @Primary
    @Bean(name = "entityManagerFactoryReferential")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder, @Qualifier("dataSourceReferential") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.circe.invoice.entity.referential")
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerReferential")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactoryReferential")EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

    @ConfigurationProperties
    public LiquibaseProperties primaryLiquibaseProperties(){
        return new LiquibaseProperties();
    }

    @Bean("primaryLiquibase")
    public SpringLiquibase primaryLiquibase(){
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("classpath:/db/changelog/referential/db.referential.changelog-master.xml");
        liquibase.setShouldRun(configuration.isLiquibaseReferentialEnabled());
        return liquibase;
    }
}
