package com.excilys.scaltot.cdb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Class allows jdbc connection ( initialization, opening and closing ).
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public class JdbcConnection {

    private Connection connection;
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConnection.class.getName());
    private Properties properties = PropertiesFile.INSTANCE.getProperties();
    private HikariDataSource dataSource;

    /**
     * Singleton's private constructor.
     */
    private JdbcConnection() {
        initDriver();
        properties.setFileName("hikari.properties");
        loadProperties.initLoadProperties();
        properties = loadProperties.getProperties();
        HikariConfig config = new HikariConfig(properties);
        config.setConnectionTestQuery("show tables");
        dataSource = new HikariDataSource(config);
    }

    /**
     * Holder setting the singleton's instance.
     *
     * @author Caltot Stéphan
     *
     *         23 févr. 2017
     */
    private static class JdbcConnectionHolder {

        private static final JdbcConnection INSTANCE = new JdbcConnection();

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
     * Retrieves singleton'instance.
     *
     * @return JdbcConnection
     */
    public static JdbcConnection getInstance() {
        return JdbcConnectionHolder.INSTANCE;
    }

    /**
     * Retrieves the connection.
     *
     * @return connection
     */
    public Connection getDriver() {
        return JdbcConnectionHolder.INSTANCE.connection;
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
            connection = DriverManager.getConnection(properties.getProperty("URL"), properties.getProperty("DB_LOGIN"),
                    properties.getProperty("DB_PASSWORD"));
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
            e.printStackTrace();
        }
    }
    
    /**
    *
    */
   public void commit() {
       try {
           connection.commit();
       } catch (SQLException e) {
           throw new PersistenceException("Error on commit on database " + e);
       }
   }

   /**
    *
    */
   public void rollback() {
       try {
           connection.rollback();
       } catch (SQLException e) {
           throw new PersistenceException("Error for rollback on database " + e);
       }
   }
}
