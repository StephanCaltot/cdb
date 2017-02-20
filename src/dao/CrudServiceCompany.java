package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.company.Company;
import interfaces.ICompany;


/**
 * Crud service allows CRUD's operations on Company entities 
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class CrudServiceCompany {

	private JdbcConnection jdbcConnection = JdbcConnection.getInstance();
	private Connection connection         = jdbcConnection.getConnection();
	@SuppressWarnings("unused")
	private Statement statement;
	private ResultSet resultSet ;
	private PreparedStatement preparedStatementInsert;
	private PreparedStatement preparedStatementFind;
	
	
	/**
	 * Constructor initializing statement
	 * @throws SQLException
	 */
	public CrudServiceCompany() throws SQLException{
		statement =  connection.createStatement();
	}
	
	
	/**
	 * Create CRUD's operation
	 * @param pCompany
	 * @throws SQLException
	 */
    public void create(ICompany pCompany) throws SQLException {
		preparedStatementInsert = connection.prepareStatement(JdbcPropreties.INSERT);
    	preparedStatementInsert.setString(1, pCompany.getName());;
    	preparedStatementInsert.execute();
    }

    
    /**
     * Find CRUD's operation
     * @param pId
     * @return company entity find with id gave in parameter
     * @throws SQLException
     */
    public ICompany find(int pId) throws SQLException {
		preparedStatementFind = connection.prepareStatement(JdbcPropreties.FIND);
    	preparedStatementFind.setInt(1, pId);
    	resultSet = preparedStatementFind.executeQuery();
    	resultSet.next();
    	String name = resultSet.getString("name");
    	int id = resultSet.getInt("id");
    	ICompany company = new Company.CompanyBuilder(name).id(id).build();
    	return company;
 
    }
   
}