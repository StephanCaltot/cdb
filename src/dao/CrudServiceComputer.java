package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Computer;
import interfaces.ICompany;
import interfaces.IComputer;

/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class CrudServiceComputer {
	
	private JdbcConnection jdbcConnection = JdbcConnection.getInstance();
	private Connection connection = jdbcConnection.getConnection();
	@SuppressWarnings("unused")
	private Statement statement;
	private ResultSet resultSet ;
	private PreparedStatement preparedStatementInsert;
	private PreparedStatement preparedStatementFind;
	private PreparedStatement preparedStatementDelete;	
	private PreparedStatement preparedStatementUpdate;
	private List<String> computers ;

	
	
	
	public CrudServiceComputer() throws SQLException{
		statement =  connection.createStatement();
	}
	
	
	
    public void create(IComputer pComputer) throws SQLException {
		preparedStatementInsert = connection.prepareStatement(DaoProperties.CREATE_COMPUTER);
    	preparedStatementInsert.setString(1, pComputer.getName());;
    	preparedStatementInsert.execute();
    }

    

    public IComputer find(int pId) throws Exception {
		preparedStatementFind = connection.prepareStatement(DaoProperties.FIND_COMPUTER);
    	preparedStatementFind.setInt(1, pId);
    	resultSet = preparedStatementFind.executeQuery();
    	IComputer computer = resultSetToEntity(resultSet);
    	return computer;
 
    }
    
    
    public void delete(int pId) throws SQLException{
		preparedStatementDelete = connection.prepareStatement(DaoProperties.DELETE_COMPUTER);
    	preparedStatementDelete.setInt(1, pId);
    	preparedStatementDelete.execute();
    }
    
    
    public void update(IComputer pComputer) throws Exception{
		preparedStatementUpdate = connection.prepareStatement(DaoProperties.UPDATE_COMPUTER);
    	preparedStatementUpdate.setString(1, pComputer.getName());
    	preparedStatementUpdate.setInt(2, pComputer.getId());
    	System.out.println(preparedStatementUpdate.toString());
    	preparedStatementUpdate.execute();
    }
    
    public List<String> findAll() throws Exception{
		computers = new ArrayList<String>();
		
    	preparedStatementFind = connection.prepareStatement(DaoProperties.FIND_ALL_COMPUTERS);
    	resultSet = preparedStatementFind.executeQuery();
    	while ( resultSet.next()){
    		computers.add(resultSet.getString("name"));
    	}
    	return computers;
    }
    
    public IComputer resultSetToEntity(ResultSet pResultSet) throws Exception{
    	resultSet.next();
    	String name = resultSet.getString("name");
    	Date introduced = resultSet.getDate("introduced");
    	Date discontinued = resultSet.getDate("discontinued");
    	IComputer computer = new Computer.ComputerBuilder(name).build();
    	computer.setId(resultSet.getInt("id"));
    	computer.setDateWichIsDiscontinued(discontinued);
    	computer.setDateWichIsIntroduced(introduced);
    	ICompany company = new CrudServiceCompany().find(resultSet.getInt("company_id"));
    	computer.setManufacturer(company);
    	return computer;
    }

}