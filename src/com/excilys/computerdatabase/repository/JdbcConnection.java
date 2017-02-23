package com.excilys.computerdatabase.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Class allows jdbc connection ( initialization, opening and closing )
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
import java.util.Properties;

import com.excilys.computerdatabase.PropertiesFile;
public class JdbcConnection {

	
	private Connection connection ;
	
	private Properties properties = new PropertiesFile().getProperties("sgbd.propreties");
		
	/**
	 * Singleton's private constructor
	 */
	private JdbcConnection(){
		initConnection();
	}
	
	
	/**
	 * Holder setting the singleton's instance
	 * @author Caltot Stéphan
	 *
	 * 23 févr. 2017
	 */
	private static class JdbcConnectionHolder {
		
		private final static JdbcConnection INSTANCE = new JdbcConnection();

	}

	
	
	/**
	 * Initialization of connection
	 * @return connection : the open connection
	 */
	public void initConnection(){
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(URL,DB_LOGIN,DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Retrieves singleton'instance
	 * @return 
	 */
	public static JdbcConnection getInstance()
	{
		return JdbcConnectionHolder.INSTANCE;
	}
	
	
	
	/**
	 * Retrieves the connection
	 * @return connection
	 */
	public Connection getConnection(){
		return JdbcConnectionHolder.INSTANCE.connection;
	}
}
