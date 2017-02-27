package com.excilys.scaltot.cdb.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Caltot Stéphan
 *
 *         23 févr. 2017
 */
public enum PropertiesFile {

    INSTANCE;

    private Properties properties;

    private static final String FILE_NAME = "/home/excilys/Documents/cdb/resources/sgbd.properties";

    /**
     * Private constructor.
     */
    PropertiesFile() {

        properties = new Properties();

        try {
            properties.load(new FileInputStream(FILE_NAME));
        } catch (IOException e) {

        }
    }

    public Properties getProperties() {
        return properties;
    }
}
