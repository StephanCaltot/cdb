package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Company;
import interfaces.ICompany;

public class CrudServiceCompany {
	
	private JdbcConnection jdbcConnection = JdbcConnection.getInstance();
	private Connection connection = jdbcConnection.getConnection();
	@SuppressWarnings("unused")
	private Statement statement;
	private ResultSet resultSet ;
	private PreparedStatement preparedStatementInsert;
	private PreparedStatement preparedStatementFind;
	
	
	public CrudServiceCompany() throws SQLException{
		statement =  connection.createStatement();
	}
	
	
	
    public void create(ICompany pCompany) throws SQLException {
		preparedStatementInsert = connection.prepareStatement("insert into company(name) values ( ? )");
    	preparedStatementInsert.setString(1, pCompany.getName());;
    	preparedStatementInsert.execute();
    }


    public ICompany find(int pId) throws SQLException {
		preparedStatementFind = connection.prepareStatement("select * from company where id= ?;");
    	preparedStatementFind.setInt(1, pId);
    	resultSet = preparedStatementFind.executeQuery();
    	resultSet.next();
    	String name = resultSet.getString("name");
    	int id = resultSet.getInt("id");
    	ICompany company = new Company.CompanyBuilder(name).id(id).build();
    	return company;
 
    }
   
}