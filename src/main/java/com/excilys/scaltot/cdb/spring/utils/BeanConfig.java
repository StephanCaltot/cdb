package com.excilys.scaltot.cdb.spring.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Caltot Stéphan
 *
 * 20 mars 2017
 */
@Configuration
@ComponentScan("com.excilys.scaltot.cdb")
@EnableTransactionManagement
//@ComponentScan(basePackages = { "com.excilys.scaltot.cdb.services.implementation", "com.excilys.scaltot.cdb.persistence.implementation" })
public class BeanConfig {

}
