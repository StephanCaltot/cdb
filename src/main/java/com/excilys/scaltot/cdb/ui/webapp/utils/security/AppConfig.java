//package com.excilys.scaltot.cdb.ui.webapp.utils.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//
///**
// *
// * @author Caltot Stéphan
// *
// * 3 avr. 2017
// */
//@EnableWebMvc
//@Configuration
//@ComponentScan({ "com.excilys.scaltot.cdb" })
//@Import({ SecurityConfig.class })
//public class AppConfig {
//
//
//    /**
//     * Configuration.
//     * @return InternalResourceViewResolver
//     */
//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/views/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
//
//}