package com.excilys.computerdatabase.repository;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public CrudServiceComputer() {
		try {
			crudServiceConstant.statement =  crudServiceConstant.connection.createStatement();
	    	crudServiceConstant.preparedStatementInsert = crudServiceConstant.connection.prepareStatement(DaoProperties.CREATE_COMPUTER);
	    	crudServiceConstant.preparedStatementDelete = crudServiceConstant.connection.prepareStatement(DaoProperties.DELETE_COMPUTER);
	    	crudServiceConstant.preparedStatementUpdate = crudServiceConstant.connection.prepareStatement(DaoProperties.UPDATE_COMPUTER);
			crudServiceConstant.preparedStatementFindAll = crudServiceConstant.connection.prepareStatement(DaoProperties.FIND_ALL_COMPUTERS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Create CRUD's operation
	 * @param pComputer
	 * @throws SQLException
	 */
    public void create(Computer computer) {
    	try {
			crudServiceConstant.preparedStatementInsert.setString(1, computer.getName());
			if (computer.getDateWichIsIntroduced() != null ){
				crudServiceConstant.preparedStatementInsert.setObject(2, Date.valueOf(computer.getDateWichIsIntroduced()));
			}
			else{
				crudServiceConstant.preparedStatementInsert.setNull(2, java.sql.Types.DATE);
			}
			if (computer.getDateWichIsDiscontinued() != null ){
				crudServiceConstant.preparedStatementInsert.setDate(3, Date.valueOf(computer.getDateWichIsDiscontinued()));
			}
			else{
				crudServiceConstant.preparedStatementInsert.setNull(3, java.sql.Types.DATE);
			}
			
			if (computer.getManufacturer() != null) {
				crudServiceConstant.preparedStatementInsert.setLong(4, computer.getManufacturer().getId());
			}
			else {
				crudServiceConstant.preparedStatementInsert.setNull(4, java.sql.Types.INTEGER);

			}
	    	crudServiceConstant.preparedStatementInsert.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		};
    }

    
    
    /**
     * Find CRUD's operation
     * @param pId
     * @return computer entity find with id gave in parameter
     * @throws Exception
     */
    public Optional <Computer> find(long id) {
    	try {
			crudServiceConstant.preparedStatementFind = crudServiceConstant.connection.prepareStatement(DaoProperties.FIND_COMPUTER);
	    	crudServiceConstant.preparedStatementFind.setLong(1, id);
	    	resultSet = crudServiceConstant.preparedStatementFind.executeQuery();
	    	resultSet.next();
		    computer = MapperComputer.resultSetToEntity(resultSet).get();
	  
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

    	return Optional.of(computer);
 
    }
    
    
    
    /**
     * Delete CRUD's operation
     * @param pId
     * @throws SQLException
     */
    public void delete(long id) {
    	try {
			crudServiceConstant.preparedStatementDelete.setLong(1, id);
	    	crudServiceConstant.preparedStatementDelete.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    
    /**
     * Update CRUD's operation
     * @param pComputer
     * @return computer entity updated
     * @throws Exception
     */
    public void update(Computer computer) {
    	try {
			crudServiceConstant.preparedStatementUpdate.setString(1, computer.getName());
	    	crudServiceConstant.preparedStatementUpdate.setLong(2, computer.getId());
	    	crudServiceConstant.preparedStatementUpdate.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    
    /**
     * Retrieves all computers without any pagination
     * @return list of computers
     * @throws Exception
     */
    public List<Computer> findAll() {
    	computers = new ArrayList<>();
    	try {
			resultSet = crudServiceConstant.preparedStatementFindAll.executeQuery();
	    	while (resultSet.next()){
	    		if (MapperComputer.resultSetToEntity(resultSet).isPresent()){
		    		computer = MapperComputer.resultSetToEntity(resultSet).get();
		    		computers.add(computer);	
	    		}
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

    	return computers;
    }
    
    
    
    /**
     * Retrieves computers paginated by limit ( 10 here )
     * @param pOffset
     * @return list of computers paginated
     * @throws Exception
     */
    public List<Computer> findByPage(long offset, int numberForEachpPage) {
    	computers = new ArrayList<>();
    	if (numberForEachpPage <= 0){
    		numberForEachpPage = crudServiceConstant.LIMIT_DEFAULT;
    	}
    	if (offset < 0){
    		offset = 0;;
    	}
		try {
			crudServiceConstant.preparedStatementFindByPage = crudServiceConstant.connection.prepareStatement(DaoProperties.PAGE_COMPUTER);
			crudServiceConstant.preparedStatementFindByPage.setInt(1, numberForEachpPage);
			crudServiceConstant.preparedStatementFindByPage.setLong(2, offset);
	    	resultSet = crudServiceConstant.preparedStatementFindByPage.executeQuery();
	    	while (resultSet.next()){
	    		if(MapperComputer.resultSetToEntity(resultSet).isPresent()){
		    		computer = MapperComputer.resultSetToEntity(resultSet).get();
		    		computers.add(computer);	
	    		}
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

    	return computers;
    }
}
