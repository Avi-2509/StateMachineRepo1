package com.springframework1.msscssm.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
        basePackages = "com.springframework1.msscssm.repository",
        entityManagerFactoryRef = "paymentGatewayEntityManagerFactory",
        transactionManagerRef = "paymentGatewayTransactionManager"
)
@EnableTransactionManagement
public class PaymentGate {

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource paymentGatewayDataSource() {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));

        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-clas-name"));

        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean paymentGatewayEntityManagerFactory(
            final EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            final @Qualifier("paymentGatewayDataSource") DataSource dataSource) {

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));


        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.springframework1.msscssm.domain")
                .persistenceUnit("paymentGatewayEntityManagerFactory")
                .properties(properties)
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager paymentGatewayTransactionManager(
            @Qualifier("paymentGatewayEntityManagerFactory") EntityManagerFactory paymentGatewayEntityManagerFactory) {
        return new JpaTransactionManager(paymentGatewayEntityManagerFactory);
    }
}
