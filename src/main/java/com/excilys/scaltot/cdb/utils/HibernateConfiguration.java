package com.excilys.scaltot.cdb.utils;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;



/**
 * @author Caltot Stéphan
 *
 * 5 avr. 2017
 */
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:config.properties" })
@ComponentScan(basePackages = { "com.excilys.scaltot.cdb" })
public class HibernateConfiguration {

    @Autowired
    private Environment env;

    /**
     * Data source.
     * @return datas ource
     */
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:sql/schema.sql")
            .addScript("classpath:sql/test-data.sql")
            .build();
    }
//
//    /**
//     * LocalSessionFactoryBean.
//     * @return LocalSessionFactoryBean
//     */
//    @Bean(name = "sessionFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan(new String[] {"com.excilys.scaltot.cdb" });
//        sessionFactory.setHibernateProperties(hibernateProperties());
//
//        return sessionFactory;
//    }
//
//    /**
//     * HibernateTransactionManager.
//     * @return HibernateTransactionManager
//     */
//    @Bean(name = "transactionManager")
//    public HibernateTransactionManager transactionManager() {
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager.setSessionFactory(sessionFactory().getObject());
//
//        return txManager;
//    }

    /**
     * Properties.
     * @return properties
     */
    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.cache.provider_class", env.getProperty("hibernate.provider_class"));
                setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl"));
            }
        };
    }
}