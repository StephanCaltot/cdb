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
public class JdbcConnection {

	
	private static final String DB_NAME      = "computer-database-db";
	private static final String DB_IP        = "localhost";
	private static final String DB_LOGIN     = "admincdb";
	private static final String DB_PASSWORD  = "qwerty1234";
	private static final String SGBD         = "jdbc:mysql";
	private static final String URL          = SGBD + "://" + DB_IP + "/" + DB_NAME + "?zeroDateTimeBehavior=convertToNull";
	private static final String DRIVER 		 = "com.mysql.jdbc.Driver";

	private Connection connection ;
	
	
	
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
