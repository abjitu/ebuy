package com.ebuy.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * The Class DatabaseConfig.
 */
@Configuration
@ComponentScan("com.ebuy")
@EnableTransactionManagement
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {

    /** The db driver. */
    private @Value("${ebuy.db.driver}") String dbDriver;

    /** The db url. */
    private @Value("${ebuy.db.url}") String dbUrl;

    /** The db user name. */
    private @Value("${ebuy.db.username}") String dbUserName;

    /** The db passwd. */
    private @Value("${ebuy.db.password}") String dbPasswd;

    /**
     * Transaction manager.
     *
     * @param entityManagerFactory the entity manager factory
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    /**
     * Entity manager factory.
     *
     * @return the local container entity manager factory bean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan("com.ebuy.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);
        entityManager.setJpaPropertyMap(hibernateProperties());
        return entityManager;
    }

    /**
     * Data source.
     *
     * @return the basic data source
     */
    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(dbPasswd);
        dataSource.setMaxIdle(10);
        dataSource.setMaxWaitMillis(1000);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setDefaultAutoCommit(true);
        dataSource.setValidationQuery("SELECT 1+1");
        dataSource.setTestOnBorrow(true);

        return dataSource;
    }

    /**
     * Hibernate properties.
     *
     * @return the map
     */
    private Map<String, Object> hibernateProperties() {
        Map<String, Object> hibernateProperties = new HashMap<String, Object>();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProperties.put("hibernate.query.substitutions", "true 'Y', false 'N'");
        hibernateProperties.put("hibernate.show_sql", "false");
        hibernateProperties.put("hibernate.format_sql", "true");

        return hibernateProperties;
    }

    /**
     * Hibernate exception translator.
     *
     * @return the hibernate exception translator
     */
    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        HibernateExceptionTranslator hibernateExceptionTranslator = new HibernateExceptionTranslator();
        return hibernateExceptionTranslator;
    }

    /**
     * Property placeholder configurer.
     *
     * @return the property sources placeholder configurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
