package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.computer.Computer;
import interfaces.ICompany;
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
	private PreparedStatement preparedStatementFindAll;
	private PreparedStatement preparedStatementFindByPage;
	private List<IComputer> computers ;
	private IComputer computer;
	private String name = null;
	private Date introduced = null;
	private Date discontinued = null;
	private int id = 0;
	public final static int LIMIT = 10;
	
	
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
		preparedStatementInsert = connection.prepareStatement(DaoProperties.CREATE_COMPUTER);
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
		preparedStatementFind = connection.prepareStatement(DaoProperties.FIND_COMPUTER);
    	preparedStatementFind.setInt(1, pId);
    	resultSet = preparedStatementFind.executeQuery();
    	resultSet.next();
    	IComputer computer = resultSetToEntity(resultSet);
    	return computer;
 
    }
    
    /**
     * Delete CRUD's operation
     * @param pId
     * @throws SQLException
     */
    public void delete(int pId) throws SQLException{
		preparedStatementDelete = connection.prepareStatement(DaoProperties.DELETE_COMPUTER);
    	preparedStatementDelete.setInt(1, pId);
    	preparedStatementDelete.execute();
    }
    
    /**
     * Update CRUD's operation
     * @param pComputer
     * @return computer entity updated
     * @throws Exception
     */
    public void update(IComputer pComputer) throws Exception{
		preparedStatementUpdate = connection.prepareStatement(DaoProperties.UPDATE_COMPUTER);
    	preparedStatementUpdate.setString(1, pComputer.getName());
    	preparedStatementUpdate.setInt(2, pComputer.getId());
    	System.out.println(preparedStatementUpdate.toString());
    	preparedStatementUpdate.execute();
    }
    
    public List<IComputer> findAll() throws Exception{
		computers = new ArrayList<IComputer>();
    	preparedStatementFindAll = connection.prepareStatement(DaoProperties.FIND_ALL_COMPUTERS);
    	resultSet = preparedStatementFindAll.executeQuery();
    	while (resultSet.next()){
    		computer = resultSetToEntity(resultSet);
    		computers.add(computer);
    	}
    	return computers;
    }
    
    
    public List<IComputer> findByPage(int pOffset) throws Exception{
		computers = new ArrayList<IComputer>();
    	preparedStatementFindByPage = connection.prepareStatement(DaoProperties.PAGE_COMPUTER);
    	preparedStatementFindByPage.setInt(1, LIMIT);
    	preparedStatementFindByPage.setInt(2, pOffset);
    	System.out.println(preparedStatementFindByPage.toString());
    	resultSet = preparedStatementFindByPage.executeQuery();
    	while (resultSet.next()){
    		computer = resultSetToEntity(resultSet);
    		computers.add(computer);
    	}
    	return computers;
    }
    
    public IComputer resultSetToEntity(ResultSet pResultSet) throws Exception{
    	

    	if ( resultSet.getInt("id") != 0  ) {
    		id = resultSet.getInt("id");
    	}
    	if (resultSet.getString("name") != null ) {
        	name = resultSet.getString("name");
    	}
    	if (resultSet.getDate("introduced") != null){
        	introduced = resultSet.getDate("introduced");
    	}
    	if (resultSet.getDate("discontinued") != null ){
        	discontinued = resultSet.getDate("discontinued");
    	}
    	IComputer computer = new Computer.ComputerBuilder(name).build();
    	computer.setId(id);
    	computer.setDateWichIsDiscontinued(discontinued);
    	computer.setDateWichIsIntroduced(introduced);
    	if (resultSet.getInt("company_id") != 0 ) {
        	ICompany company = new CrudServiceCompany().find(resultSet.getInt("company_id"));
        	computer.setManufacturer(company);	
    	}
    	
    	return computer;
    }

}