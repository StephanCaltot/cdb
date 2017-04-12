package com.excilys.scaltot.cdb.webservices.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @author Caltot Stéphan
 *
 * 1 mars 2017
 */
@Configuration
@EnableWebMvc
@ComponentScan({ "com.excilys.scaltot.cdb" })
@EnableTransactionManagement
public class AppConfiguration extends WebMvcConfigurerAdapter{

}