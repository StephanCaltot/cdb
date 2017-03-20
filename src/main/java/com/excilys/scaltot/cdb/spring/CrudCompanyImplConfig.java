package com.excilys.scaltot.cdb.spring;

import org.springframework.context.annotation.Bean;

import com.excilys.scaltot.cdb.persistence.impl.CrudCompanyImpl;

/**
 * @author Caltot Stéphan
 *
 * 20 mars 2017
 */
public class CrudCompanyImplConfig {

    @Bean
    public CrudCompanyImpl crudCompanyImpl(){
        return CrudCompanyImpl.INSTANCE;
    }
}
