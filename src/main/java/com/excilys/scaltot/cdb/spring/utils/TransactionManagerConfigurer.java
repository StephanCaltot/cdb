package com.excilys.scaltot.cdb.spring.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.excilys.scaltot.cdb.utils.Datasource;

/**
 * @author Caltot Stéphan
 *
 * 23 mars 2017
 */
@Repository
public class TransactionManagerConfigurer implements TransactionManagementConfigurer {

    @Autowired
    private Datasource datasource;

    /**
     * Constructor instantiating the data source.
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(datasource);
    }

}
