package com.msp.webportal.businesslogic.config;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import javax.persistence.EntityManagerFactory;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.msp.webportal.businesslogic.repository"},
        entityManagerFactoryRef = "entityManagerFactory1",
        transactionManagerRef = "db1Transaction"
)
public class Db1Config {

    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource1")
    @Primary
    public DataSource dataSource1() {
        System.out.println("created bean dataSource1 !!!");
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory2(
            @Qualifier("dataSource1") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("com.msp.webportal.businesslogic.model");

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        factoryBean.setJpaPropertyMap(properties);

        return factoryBean;
    }

//    @Bean(name = "entityManagerFactory1")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory2(
//            @Qualifier("dataSource1") DataSource dataSource, JpaProperties jpaProperties) {
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        factoryBean.setPackagesToScan("com.msp.webportal.businesslogic.model");
//        Map<String, Object> properties = new HashMap<>(jpaProperties.getProperties());
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        factoryBean.setJpaPropertyMap(properties);
//        return factoryBean;
//    }

    @Primary
    @Bean(name = "db1Transaction")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("entityManagerFactory1") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


}