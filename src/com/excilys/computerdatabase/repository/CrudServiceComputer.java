package com.excilys.computerdatabase.repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.excilys.computerdatabase.entities.company.Company;
import com.excilys.computerdatabase.entities.computer.Computer;

/**
 * Crud service allows CRUD operations on Computer entities 
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class CrudServiceComputer implements CrudService<Computer> {
	

	private ResultSet resultSet ;
	private Computer computer;
	private List<Computer> computers;
	
	
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
    public void create(Computer computer) throws SQLException {
    	crudServiceConstant.preparedStatementInsert = crudServiceConstant.connection.prepareStatement(DaoProperties.CREATE_COMPUTER);
    	crudServiceConstant.preparedStatementInsert.setString(1, computer.getName());;
    	crudServiceConstant.preparedStatementInsert.execute();
    }

    
    
    /**
     * Find CRUD's operation
     * @param pId
     * @return computer entity find with id gave in parameter
     * @throws Exception
     */
    public Computer find(long id) throws Exception {
    	crudServiceConstant.preparedStatementFind = crudServiceConstant.connection.prepareStatement(DaoProperties.FIND_COMPUTER);
    	crudServiceConstant.preparedStatementFind.setLong(1, id);
    	resultSet = crudServiceConstant.preparedStatementFind.executeQuery();
    	resultSet.next();
    	Computer computer = resultSetToEntity();
    	return computer;
 
    }
    
    
    
    /**
     * Delete CRUD's operation
     * @param pId
     * @throws SQLException
     */
    public void delete(long id) throws SQLException{
    	crudServiceConstant.preparedStatementDelete = crudServiceConstant.connection.prepareStatement(DaoProperties.DELETE_COMPUTER);
    	crudServiceConstant.preparedStatementDelete.setLong(1, id);
    	crudServiceConstant.preparedStatementDelete.execute();
    }
    
    
    
    /**
     * Update CRUD's operation
     * @param pComputer
     * @return computer entity updated
     * @throws Exception
     */
    public void update(Computer computer) throws Exception{
    	crudServiceConstant.preparedStatementUpdate = crudServiceConstant.connection.prepareStatement(DaoProperties.UPDATE_COMPUTER);
    	crudServiceConstant.preparedStatementUpdate.setString(1, computer.getName());
    	crudServiceConstant.preparedStatementUpdate.setLong(2, computer.getId());
    	crudServiceConstant.preparedStatementUpdate.execute();
    }
    
    
    
    /**
     * Retrieves all computers without any pagination
     * @return list of computers
     * @throws Exception
     */
    public List<Computer> findAll() throws Exception{
    	computers = new ArrayList<>();
		crudServiceConstant.preparedStatementFindAll = crudServiceConstant.connection.prepareStatement(DaoProperties.FIND_ALL_COMPUTERS);
    	resultSet = crudServiceConstant.preparedStatementFindAll.executeQuery();
    	while (resultSet.next()){
    		computer = resultSetToEntity();
    		computers.add(computer);
    	}
    	return computers;
    }
    
    
    
    /**
     * Retrieves computers paginated by limit ( 10 here )
     * @param pOffset
     * @return list of computers paginated
     * @throws Exception
     */
    public List<Computer> findByPage(long offset) throws Exception{
    	computers = new ArrayList<>();
		crudServiceConstant.preparedStatementFindByPage = crudServiceConstant.connection.prepareStatement(DaoProperties.PAGE_COMPUTER);
		crudServiceConstant.preparedStatementFindByPage.setInt(1, crudServiceConstant.LIMIT);
		crudServiceConstant.preparedStatementFindByPage.setLong(2, offset);
    	resultSet = crudServiceConstant.preparedStatementFindByPage.executeQuery();
    	while (resultSet.next()){
    		computer = resultSetToEntity();
    		computers.add(computer);
    	}
    	return computers;
    }
    
    
    
    /**
     * Method transform resultSet in computer entity
     * @param pResultSet
     * @return IComputer
     * @throws Exception
     */
    public Computer resultSetToEntity() throws Exception{
    	
    	long id = 0;
    	String name = null;
    	Date introduced = null;
    	Date discontinued = null;
    	
    	if ( resultSet.getLong("id") != 0  ) {
    		id = resultSet.getLong("id");
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
    	Computer computer = new Computer.Builder().withName(name).build();
		computer.setId(id);
    	computer.setDateWichIsDiscontinued(discontinued);
    	computer.setDateWichIsIntroduced(introduced);
    	if (resultSet.getInt("company_id") != 0 ) {
        	Company company = new CrudServiceCompany().find(resultSet.getInt("company_id"));
        	computer.setManufacturer(company);	
    	}
    	
    	return computer;
    }

}