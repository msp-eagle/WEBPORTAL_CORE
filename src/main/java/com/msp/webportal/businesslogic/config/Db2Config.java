package com.msp.webportal.businesslogic.config;
//import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        basePackages = "com.msp.webportal.businesslogic.idrepoRepository",
        entityManagerFactoryRef = "entityManagerFactory2",
        transactionManagerRef = "db2Transaction"
)
public class Db2Config {

    @Bean(name = "dataSource2")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource dataSource2() {
        System.out.println("created bean dataSource2 !!!");
        return DataSourceBuilder.create().build();
    }

//    @Bean(name = "entityManagerFactory2")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory2(
//            @Qualifier("dataSource2") DataSource dataSource, JpaProperties jpaProperties) {
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        factoryBean.setPackagesToScan("com.msp.webportal.businesslogic.idrepoEntity");
//        Map<String, Object> properties = new HashMap<>(jpaProperties.getProperties());
//        properties.put("hibernate.hbm2ddl.auto", "none");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        properties.put("hibernate.default_schema", "idmap");
//        factoryBean.setJpaPropertyMap(properties);
//        return factoryBean;
//    }

    @Bean(name = "entityManagerFactory2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory2(
            @Qualifier("dataSource2") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("com.msp.webportal.businesslogic.idrepoEntity");

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        factoryBean.setJpaPropertyMap(properties);

        return factoryBean;
    }


    @Bean(name= "db2Transaction")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("entityManagerFactory2") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

}
