package com.excilys.scaltot.cdb.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.excilys.scaltot.cdb.exceptions.FileNotFoundException;

/**
 * @author Caltot Stéphan
 *
 *         23 févr. 2017
 */
public enum PropertiesFile {

    INSTANCE;

    private Properties properties;
    private static final  String FILE_NAME = "sgbd.properties";
    private static final String FILE_PATH = "/home/screetts/Documents/cdb/resources/" + FILE_NAME;

    /**
     * Private constructor.
     */
    PropertiesFile() {

        properties = new Properties();

        try {
            properties.load(new FileInputStream(FILE_PATH));
        } catch (IOException e) {
            throw new FileNotFoundException("File " + FILE_NAME + "not found", e);
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
