package com.excilys.computerdatabase;

import java.io.InputStream;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * @author Caltot Stéphan
 *
 * 23 févr. 2017
 */
public class PropertiesFile {
	static InputStream inputStream;
 
	Properties properties;
	
	public Properties getProperties(String nameOfFile) throws IOException {
 
		try {
			properties = new Properties();
			String propFileName = "sgbd.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream("computer_database/resources/sgbd.properties");
 
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return properties;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		PropertiesFile propertiesFile = new PropertiesFile();
		Properties properties =  propertiesFile.getProperties("sgbd.properties");
		System.out.println(properties.getProperty("DB_NAME"));
		inputStream.close();

	}
}	 