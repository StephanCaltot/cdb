package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import interfaces.ICompany;
import interfaces.IComputer;


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

	static JdbcConnection jdbcConnection = JdbcConnection.getInstance();
	static Connection connection	      = jdbcConnection.getConnection();
	static Statement statement;
	static ResultSet resultSet;
	static PreparedStatement preparedStatementInsert;
	static PreparedStatement preparedStatementFind;
	static PreparedStatement preparedStatementDelete;	
	static PreparedStatement preparedStatementUpdate;
	static PreparedStatement preparedStatementFindAll;
	static PreparedStatement preparedStatementFindByPage;
	static List<IComputer> computers ;
	static List<ICompany>  companies ;
	static IComputer computer;
	static ICompany company;
	static String name       = null;
	static Date introduced   = null;
	static Date discontinued = null;
	static int id = 0;

}
