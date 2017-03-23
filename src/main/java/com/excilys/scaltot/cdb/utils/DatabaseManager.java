package com.excilys.scaltot.cdb.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.scaltot.cdb.exceptions.PersistenceException;

/**
 * Class allows jdbc connection ( initialization, opening and closing ).
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
@Component
@Scope("singleton")
public class DatabaseManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseManager.class.getName());

    private Connection connection;
    @Autowired
    private Datasource datasource;

    /**
     * Constructor.
     */
    public DatabaseManager() {

    }

    /**
     * Retrieves the initialized connection.
     *
     * @return Connection
     * @throws PersistenceException : persistence exception
     * @throws SQLException : SQL exception
     */
    public Connection getConnection() {
        try {
            connection = datasource.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new PersistenceException(
                    "Connection can't be etablished and retrieved... (check your connection's properties)", e);
        }
        if (!connection.equals(null)) {
            LOGGER.info("Connection etablished and retrieved");
        }
        return connection;
    }

    /**
     * Close connection method.
     */
    public void closeConnection() {
        try {
            connection.close();
            LOGGER.info("Connection closed");
        } catch (SQLException e) {
            throw new PersistenceException("Error on close connection " + e);
        }
    }

   /**
    * Commit method for connection.
    */
   public void commit() {
       try {
           connection.commit();
       } catch (SQLException e) {
           throw new PersistenceException("Error on commit on database " + e);
       }
   }

   /**
    * Roll back method for connection.
    */
   public void rollback() {
       try {
           connection.rollback();
       } catch (SQLException e) {
           throw new PersistenceException("Error for rollback on database " + e);
       }
   }
}
