package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entities.Computer;
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
	
	
	
	
	public CrudServiceComputer() throws SQLException{
		statement =  connection.createStatement();
	}
	
	
	
    public void create(IComputer pComputer) throws SQLException {
		preparedStatementInsert = connection.prepareStatement("insert into computer(name) values ( ? )");
    	preparedStatementInsert.setString(1, pComputer.getName());;
    	preparedStatementInsert.execute();
    }

    

    public IComputer find(int pId) throws Exception {
		preparedStatementFind = connection.prepareStatement("select * from computer where id= ?;");
    	preparedStatementFind.setInt(1, pId);
    	resultSet = preparedStatementFind.executeQuery();
    	resultSet.next();
    	String name = resultSet.getString("name");
    	int id = resultSet.getInt("id");
    	IComputer computer = new Computer.ComputerBuilder(name).id(id).build();
    	return computer;
 
    }
    
    
    public void delete(int pId) throws SQLException{
		preparedStatementDelete = connection.prepareStatement("delete from computer where id= ?;");
    	preparedStatementDelete.setInt(1, pId);
    	preparedStatementDelete.execute();
    }
    
    
    public IComputer update(IComputer pComputer) throws Exception{
		preparedStatementUpdate = connection.prepareStatement("update computer set name = ? where id = ?;");
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