package com.excilys.scaltot.cdb.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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

    private Connection connection;
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseManager.class.getName());
    private PropertiesLoader propertiesLoader = PropertiesLoader.INSTANCE;
    private Properties properties;
    private HikariDataSource dataSource;
    private ThreadLocal<Connection> myThreadLocal = new ThreadLocal<Connection>();

    /**
     * Singleton's private constructor.
     */
    public DatabaseManager() {
        propertiesLoader.setFileName("hikari.properties");
        propertiesLoader.initProperties();
        properties = propertiesLoader.getProperties();
        HikariConfig config = new HikariConfig(properties);
        config.setConnectionTestQuery("show tables");
        dataSource = new HikariDataSource(config);
    }

    /**
     * Initialization of driver.
     */
    public void initDriver() {
        try {
            Class.forName(properties.getProperty("DRIVER"));
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
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
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            myThreadLocal.set(connection);
        } catch (SQLException e) {
            throw new PersistenceException(
                    "Connection can't be etablished and retrieved... (check your connection's properties)", e);
        }
        if (!connection.equals(null)) {
            LOGGER.info("Connection etablished and retrieved");
        }
        return (Connection) myThreadLocal.get();
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
