package com.excilys.computerdatabase.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.excilys.computerdatabase.entities.company.Company;
import com.excilys.computerdatabase.entities.computer.Computer;



/**
 * Class contains all needed constant for crudService operation
 * @author screetts
 *
 */
public class crudServiceConstant {

	/**
	 * Default Limit of pagination
	 */
	public static final int LIMIT_DEFAULT = 10;

	public static JdbcConnection jdbcConnection = JdbcConnection.getInstance();
	public static Connection connection = jdbcConnection.getConnection();
	public static Statement statement;
	public static ResultSet resultSet;
	public static PreparedStatement preparedStatementInsert;
	public static PreparedStatement preparedStatementFind;
	public static PreparedStatement preparedStatementDelete;	
	public static PreparedStatement preparedStatementUpdate;
	public static PreparedStatement preparedStatementFindAll;
	public static PreparedStatement preparedStatementFindByPage;
	public static List<Computer> computers ;
	public static List<Company>  companies ;

}
