package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	

	private ResultSet resultSet ;
	
	
	
	/**
	 * Constructor initializing statement
	 * @throws SQLException
	 */
	public CrudServiceComputer() throws SQLException{
		crudServiceConstant.statement =  crudServiceConstant.connection.createStatement();
	}
	
	
	
	/**
	 * Create CRUD's operation
	 * @param pComputer
	 * @throws SQLException
	 */
    public void create(IComputer pComputer) throws SQLException {
    	crudServiceConstant.preparedStatementInsert = crudServiceConstant.connection.prepareStatement(DaoProperties.CREATE_COMPUTER);
    	crudServiceConstant.preparedStatementInsert.setString(1, pComputer.getName());;
    	crudServiceConstant.preparedStatementInsert.execute();
    }

    
    
    /**
     * Find CRUD's operation
     * @param pId
     * @return computer entity find with id gave in parameter
     * @throws Exception
     */
    public IComputer find(int pId) throws Exception {
    	crudServiceConstant.preparedStatementFind = crudServiceConstant.connection.prepareStatement(DaoProperties.FIND_COMPUTER);
    	crudServiceConstant.preparedStatementFind.setInt(1, pId);
    	resultSet = crudServiceConstant.preparedStatementFind.executeQuery();
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
    	crudServiceConstant.preparedStatementDelete = crudServiceConstant.connection.prepareStatement(DaoProperties.DELETE_COMPUTER);
    	crudServiceConstant.preparedStatementDelete.setInt(1, pId);
    	crudServiceConstant.preparedStatementDelete.execute();
    }
    
    
    
    /**
     * Update CRUD's operation
     * @param pComputer
     * @return computer entity updated
     * @throws Exception
     */
    public void update(IComputer pComputer) throws Exception{
    	crudServiceConstant.preparedStatementUpdate = crudServiceConstant.connection.prepareStatement(DaoProperties.UPDATE_COMPUTER);
    	crudServiceConstant.preparedStatementUpdate.setString(1, pComputer.getName());
    	crudServiceConstant.preparedStatementUpdate.setInt(2, pComputer.getId());
    	crudServiceConstant.preparedStatementUpdate.execute();
    }
    
    
    
    /**
     * Retrieves all computers without any pagination
     * @return list of computers
     * @throws Exception
     */
    public List<IComputer> findAll() throws Exception{
    	crudServiceConstant.computers = new ArrayList<IComputer>();
		crudServiceConstant.preparedStatementFindAll = crudServiceConstant.connection.prepareStatement(DaoProperties.FIND_ALL_COMPUTERS);
    	resultSet = crudServiceConstant.preparedStatementFindAll.executeQuery();
    	while (resultSet.next()){
    		crudServiceConstant.computer = resultSetToEntity(resultSet);
    		crudServiceConstant.computers.add(crudServiceConstant.computer);
    	}
    	return crudServiceConstant.computers;
    }
    
    
    
    /**
     * Retrieves computers paginated by limit ( 10 here )
     * @param pOffset
     * @return list of computers paginated
     * @throws Exception
     */
    public List<IComputer> findByPage(int pOffset) throws Exception{
    	crudServiceConstant.computers = new ArrayList<IComputer>();
		crudServiceConstant.preparedStatementFindByPage = crudServiceConstant.connection.prepareStatement(DaoProperties.PAGE_COMPUTER);
		crudServiceConstant.preparedStatementFindByPage.setInt(1, crudServiceConstant.LIMIT);
		crudServiceConstant.preparedStatementFindByPage.setInt(2, pOffset);
    	resultSet = crudServiceConstant.preparedStatementFindByPage.executeQuery();
    	while (resultSet.next()){
    		crudServiceConstant.computer = resultSetToEntity(resultSet);
    		crudServiceConstant.computers.add(crudServiceConstant.computer);
    	}
    	return crudServiceConstant.computers;
    }
    
    
    
    /**
     * Method transform resultSet in computer entity
     * @param pResultSet
     * @return IComputer
     * @throws Exception
     */
    public IComputer resultSetToEntity(ResultSet pResultSet) throws Exception{

    	if ( resultSet.getInt("id") != 0  ) {
    		crudServiceConstant.id = resultSet.getInt("id");
    	}
    	if (resultSet.getString("name") != null ) {
    		crudServiceConstant.name = resultSet.getString("name");
    	}
    	if (resultSet.getDate("introduced") != null){
    		crudServiceConstant.introduced = resultSet.getDate("introduced");
    	}
    	if (resultSet.getDate("discontinued") != null ){
    		crudServiceConstant.discontinued = resultSet.getDate("discontinued");
    	}
    	IComputer computer = new Computer.ComputerBuilder(crudServiceConstant.name).build();
    	computer.setId(crudServiceConstant.id);
    	computer.setDateWichIsDiscontinued(crudServiceConstant.discontinued);
    	computer.setDateWichIsIntroduced(crudServiceConstant.introduced);
    	if (resultSet.getInt("company_id") != 0 ) {
        	ICompany company = new CrudServiceCompany().find(resultSet.getInt("company_id"));
        	computer.setManufacturer(company);	
    	}
    	
    	return computer;
    }

}