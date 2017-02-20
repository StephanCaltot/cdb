package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class JdbcConnection {

	private static final JdbcConnection JDBC_CONNECTION = new JdbcConnection();
	
	private static final String DB_NAME      = "computer-database-db";
	private static final String DB_IP        = "localhost";
	private static final String DB_LOGIN     = "admincdb";
	private static final String DB_PASSWORD  = "qwerty1234";
	private static final String URL          = "jdbc:mysql://" + DB_IP + "/" + DB_NAME;
	private static final String DRIVER 		 = "com.mysql.jdbc.Driver";

	private Connection connection ;
	
	
	
	private JdbcConnection(){
		getConnection();
	}
	
	
	
	public Connection getConnection(){
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

		return connection;
	}
	
	
	public void closeConnection (){
		try {
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static JdbcConnection getInstance(){
		return JDBC_CONNECTION;
	}
}
