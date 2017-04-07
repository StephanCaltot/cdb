package com.excilys.scaltot.cdb.webapp.utils.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * @author Caltot Stéphan
 *
 * 3 avr. 2017
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * SpringSecrutiy configuration.
     * @param auth : AuthenticationManagerBuilder
     * @throws Exception : exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.inMemoryAuthentication().withUser("scaltot").password("123456").roles("USER");
      auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

      http.authorizeRequests()
        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
        .and().formLogin();
    }
}