package com.excilys.scaltot.cdb.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.excilys.scaltot.cdb.exceptions.FileNotFoundException;

/**
 * @author Caltot Stéphan
 *
 *         23 févr. 2017
 */
public enum PropertiesLoader {

    INSTANCE;

    private Properties properties;
    private String fileName;

    /**
     * Private constructor.
     */
    PropertiesLoader() {

    }

    /**
     * Initialize properties.
     */
    public void initProperties() {
        properties = new Properties();
        InputStream input = null;
        input = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(input);
        } catch (IOException e) {
            throw new FileNotFoundException("File " + fileName + "not found", e);
        }
    }

    /**
     * Get properties.
     * @return properties.
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName : the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
