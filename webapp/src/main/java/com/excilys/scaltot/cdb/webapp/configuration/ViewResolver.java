package com.excilys.scaltot.cdb.webapp.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Caltot Stéphan
 *
 * 10 avr. 2017
 */
@Component
public class ViewResolver extends InternalResourceViewResolver{
    public ViewResolver () {
        this.setPrefix("/views/");
        this.setSuffix(".jsp");
    }
}