package com.excilys.scaltot.cdb.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.excilys.scaltot.cdb.persistence.impl.CrudCompanyImpl;
import com.excilys.scaltot.cdb.persistence.impl.CrudComputerImpl;
import com.excilys.scaltot.cdb.services.CrudCompanyService;
import com.excilys.scaltot.cdb.services.CrudComputerService;
import com.excilys.scaltot.cdb.services.PaginationComputerService;
import com.excilys.scaltot.cdb.utils.DatabaseManager;

/**
 * @author Caltot Stéphan
 *
 * 20 mars 2017
 */
@Configuration
@ComponentScan("com.excilys.scaltot.cdb")
public class BeanConfig {

//    /**
//     * Constructor.
//     * @return databaseManager
//     */
//    @Bean
//    public DatabaseManager databaseManager() {
//       return new DatabaseManager();
//    }
//
//    /**
//     * Constructor.
//     * @return CrudComputerService
//     */
//    @Bean
//    public CrudComputerService crudComputerService() {
//        return new CrudComputerService();
//    }
//
//    /**
//     * Constructor.
//     * @return CrudCompanyService
//     */
//    @Bean
//    public CrudCompanyService crudCompanyService() {
//        return new CrudCompanyService();
//    }
//
//    /**
//     * Constructor.
//     * @return CrudComputerImpl
//     */
//    @Bean
//    public CrudComputerImpl crudComputerImpl() {
//        return new CrudComputerImpl();
//    }
//
//    /**
//     * Constructor.
//     * @return CrudCompanyImpl
//     */
//    @Bean
//    public CrudCompanyImpl crudCompanyImpl() {
//        return new CrudCompanyImpl();
//    }
//
//    /**
//     * Constructor.
//     * @return PaginationComputerService
//     */
//    @Bean
//    public PaginationComputerService paginationComputerService() {
//        return new PaginationComputerService();
//    }
}
