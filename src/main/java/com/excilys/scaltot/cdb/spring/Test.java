package com.excilys.scaltot.cdb.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.scaltot.cdb.services.CrudComputerService;

/**
 * @author Caltot Stéphan
 *
 * 21 mars 2017
 */
public class Test {

    /**
     * @param args : args
     */
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        CrudComputerService crudComputerService = context.getBean(CrudComputerService.class);
        System.out.println(crudComputerService.find(1).get().toString());
    }
}
