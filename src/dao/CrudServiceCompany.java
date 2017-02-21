package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	private List<String> companies ;

	
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
		preparedStatementInsert = connection.prepareStatement(DaoProperties.CREATE_COMPANY);
    	preparedStatementInsert.setString(1, pCompany.getName());;
    	preparedStatementInsert.execute();
    }

	/**
	 * Find CRUD's operation
	 * @param pId
	 * @return company entity find with id gave in parameter
	 * @throws SQLException
	 */
    public ICompany find(int pId) throws Exception {
		preparedStatementFind = connection.prepareStatement(DaoProperties.FIND_COMPANY);    
    	preparedStatementFind.setInt(1, pId);
    	resultSet = preparedStatementFind.executeQuery();
    	ICompany company = resultSetToEntity(resultSet);
    	return company;
 
    }
    
    /**
     * Retrieves all companies
     * @return list contains all companies
     * @throws Exception
     */
    public List<String> findAll() throws Exception{
		companies = new ArrayList<String>();
		
    	preparedStatementFind = connection.prepareStatement(DaoProperties.FIND_ALL_COMPANIES);
    	resultSet = preparedStatementFind.executeQuery();
    	while ( resultSet.next()){
    		companies.add(resultSet.getString("name"));
    	}
    	return companies;
    }
   
    /**
     * Transforms result retrieved in new company entity
     * @param pResultSet
     * @return company entity
     * @throws Exception
     */
    public ICompany resultSetToEntity(ResultSet pResultSet) throws Exception{
    	resultSet.next();
    	String name = resultSet.getString("name");
    	ICompany company = new Company.CompanyBuilder(name).build();
    	company.setId(resultSet.getInt("id"));
    	return company;
    }
}