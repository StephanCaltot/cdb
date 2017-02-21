package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Company;
import entities.Computer;
import interfaces.ICompany;
import interfaces.IComputer;

public class CrudServiceCompany {
	
	private JdbcConnection jdbcConnection = JdbcConnection.getInstance();
	private Connection connection = jdbcConnection.getConnection();
	@SuppressWarnings("unused")
	private Statement statement;
	private ResultSet resultSet ;
	private PreparedStatement preparedStatementInsert;
	private PreparedStatement preparedStatementFind;
	private List<String> companies ;

	
	public CrudServiceCompany() throws SQLException{
		statement =  connection.createStatement();
	}
	
	
	
    public void create(ICompany pCompany) throws SQLException {
		preparedStatementInsert = connection.prepareStatement(DaoProperties.CREATE_COMPANY);
    	preparedStatementInsert.setString(1, pCompany.getName());;
    	preparedStatementInsert.execute();
    }


    public ICompany find(int pId) throws Exception {
		preparedStatementFind = connection.prepareStatement(DaoProperties.FIND_COMPANY);
    	preparedStatementFind.setInt(1, pId);
    	resultSet = preparedStatementFind.executeQuery();
    	ICompany company = resultSetToEntity(resultSet);
    	return company;
 
    }
    
    
    public List<String> findAll() throws Exception{
		companies = new ArrayList<String>();
		
    	preparedStatementFind = connection.prepareStatement(DaoProperties.FIND_ALL_COMPANIES);
    	resultSet = preparedStatementFind.executeQuery();
    	while ( resultSet.next()){
    		companies.add(resultSet.getString("name"));
    	}
    	return companies;
    }
   
    
    public ICompany resultSetToEntity(ResultSet pResultSet) throws Exception{
    	resultSet.next();
    	String name = resultSet.getString("name");
    	ICompany company = new Company.CompanyBuilder(name).build();
    	company.setId(resultSet.getInt("id"));
    	return company;
    }
}