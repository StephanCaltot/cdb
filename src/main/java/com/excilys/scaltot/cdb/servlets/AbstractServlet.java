//package com.excilys.scaltot.cdb.servlets;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;
//
///**
// * @author Caltot Stéphan
// *
// * 21 mars 2017
// */
//public class AbstractServlet extends HttpServlet {
//
//    /**
//     * serialVersionUID.
//     */
//    private static final long serialVersionUID = 1L;
//
//    protected AutowireCapableBeanFactory ctx;
//
//    /**
//     *  Initializes context.
//     */
//    @Override
//    public void init(ServletConfig  config) throws ServletException {
//        super.init()
//        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
//    }
//}
