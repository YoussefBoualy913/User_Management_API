
package com.usermanagement.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.usermanagement.repository"
)
@PropertySource("classpath:application.properties")
public class PersistenceConfig {

    private final Environment environment;

    @Autowired
    public PersistenceConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        config.setDriverClassName(
                environment.getRequiredProperty("db.driver")
        );

        config.setJdbcUrl(
                environment.getRequiredProperty("db.url")
        );

        config.setUsername(
                environment.getRequiredProperty("db.username")
        );

        config.setPassword(
                environment.getRequiredProperty("db.password")
        );

        config.setMaximumPoolSize(10);
        config.setPoolName("UserManagementPool");

        return new HikariDataSource(config);
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSource);

        factoryBean.setPackagesToScan(
                "com.usermanagement.entity"
        );

        factoryBean.setJpaVendorAdapter(
                new HibernateJpaVendorAdapter()
        );

        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        factoryBean.setJpaProperties(properties);

        return factoryBean;
    }


    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}

