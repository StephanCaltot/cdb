package com.excilys.scaltot.cdb.persistence.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

 /**
  *
  * @author Caltot Stéphan
  *
  * 22 mars 2017
  */
@Repository
@Scope("singleton")
public class Datasource extends DriverManagerDataSource {

    /**
     * Configures data source.
     */
    public Datasource() {
        this.setDriverClassName("com.mysql.jdbc.Driver");
        this.setUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull");
        this.setUsername("admincdb");
        this.setPassword("qwerty1234");
    }
}