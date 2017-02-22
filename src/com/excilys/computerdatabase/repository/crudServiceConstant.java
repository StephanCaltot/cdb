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
	 * Limit of pagination
	 */
	static final int LIMIT = 10;

	static JdbcConnection jdbcConnection = JdbcConnection.INSTANCE;
	static Connection connection = jdbcConnection.getConnection();
	static Statement statement;
	static ResultSet resultSet;
	static PreparedStatement preparedStatementInsert;
	static PreparedStatement preparedStatementFind;
	static PreparedStatement preparedStatementDelete;	
	static PreparedStatement preparedStatementUpdate;
	static PreparedStatement preparedStatementFindAll;
	static PreparedStatement preparedStatementFindByPage;
	static List<Computer> computers ;
	static List<Company>  companies ;

}
