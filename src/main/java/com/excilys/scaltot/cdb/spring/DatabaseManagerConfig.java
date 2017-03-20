package com.excilys.scaltot.cdb.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.excilys.scaltot.cdb.utils.DatabaseManager;

/**
 * @author Caltot Stéphan
 *
 * 20 mars 2017
 */
@Configuration
public class DatabaseManagerConfig {

   /**
    * Constructor.
    * @return databaseManager
    */
   @Bean
   public DatabaseManager databaseManager() {
      return new DatabaseManager();
   }
}