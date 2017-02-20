package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.computer.Computer;
import interfaces.IComputer;

/**
 * Crud service allows CRUD operations on Computer entities 
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class CrudServiceComputer {
	
	private JdbcConnection jdbcConnection = JdbcConnection.getInstance();
	private Connection connection	      = jdbcConnection.getConnection();
	@SuppressWarnings("unused")
	private Statement statement;
	private ResultSet resultSet ;
	private PreparedStatement preparedStatementInsert;
	private PreparedStatement preparedStatementFind;
	private PreparedStatement preparedStatementDelete;	
	private PreparedStatement preparedStatementUpdate;
	
	
	
	
	/**
	 * Constructor initializing statement
	 * @throws SQLException
	 */
	public CrudServiceComputer() throws SQLException{
		statement =  connection.createStatement();
	}
	
	
	/**
	 * Create CRUD's operation
	 * @param pComputer
	 * @throws SQLException
	 */
    public void create(IComputer pComputer) throws SQLException {
		preparedStatementInsert = connection.prepareStatement(JdbcPropreties.INSERT);
    	preparedStatementInsert.setString(1, pComputer.getName());;
    	preparedStatementInsert.execute();
    }

    
    /**
     * Find CRUD's operation
     * @param pId
     * @return computer entity find with id gave in parameter
     * @throws Exception
     */
    public IComputer find(int pId) throws Exception {
		preparedStatementFind = connection.prepareStatement(JdbcPropreties.FIND);
    	preparedStatementFind.setInt(1, pId);
    	resultSet = preparedStatementFind.executeQuery();
    	resultSet.next();
    	String name = resultSet.getString("name");
    	int id = resultSet.getInt("id");
    	IComputer computer = new Computer.ComputerBuilder(name).id(id).build();
    	return computer;
 
    }
    
    /**
     * Delete CRUD's operation
     * @param pId
     * @throws SQLException
     */
    public void delete(int pId) throws SQLException{
		preparedStatementDelete = connection.prepareStatement(JdbcPropreties.DELETE);
    	preparedStatementDelete.setInt(1, pId);
    	preparedStatementDelete.execute();
    }
    
    /**
     * Update CRUD's operation
     * @param pComputer
     * @return computer entity updated
     * @throws Exception
     */
    public IComputer update(IComputer pComputer) throws Exception{
		preparedStatementUpdate = connection.prepareStatement(JdbcPropreties.UPDATE);
    	preparedStatementUpdate.setString(1, pComputer.getName());;
    	resultSet = preparedStatementFind.executeQuery();
    	resultSet.next();
    	IComputer computer = find(pComputer.getId());
    	String name = resultSet.getString("name");
    	int id = resultSet.getInt("id");
    	computer = new Computer.ComputerBuilder(name).id(id).build();
    	return computer;
    }

}