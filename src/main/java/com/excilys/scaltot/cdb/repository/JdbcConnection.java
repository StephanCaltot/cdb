package com.excilys.scaltot.cdb.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.scaltot.cdb.PropertiesFile;

/**
 * Class allows jdbc connection ( initialization, opening and closing ).
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public class JdbcConnection {

    private Connection connection;

    private Properties properties = PropertiesFile.INSTANCE.getProperties();

    /**
     * Singleton's private constructor.
     */
    private JdbcConnection() {
        initDriver();
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
     */
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(properties.getProperty("URL"), properties.getProperty("DB_LOGIN"),
                    properties.getProperty("DB_PASSWORD"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Close connection method.
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
