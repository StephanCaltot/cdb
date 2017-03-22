package com.excilys.scaltot.cdb.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * @author Caltot Stéphan
 *
 * 22 mars 2017
 */
public abstract class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
          config.getServletContext());
    }
}