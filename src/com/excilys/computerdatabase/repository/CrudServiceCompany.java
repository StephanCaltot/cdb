package com.excilys.computerdatabase.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Logger;
import java.util.Optional;

import com.excilys.computerdatabase.entities.company.Company;


/**
 * Crud service allows CRUD's operations on Company entities 
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class CrudServiceCompany implements CrudService<Company> {

//	private final static Logger LOGGER = Logger.getLogger(CrudServiceCompany.class.getName());
	private ResultSet resultSet ;
	private Company company;
	private List<Company> companies;
	
	
	
	/**
	 * Constructor initializing statement
	 * @throws SQLException
	 */
	public CrudServiceCompany() {
		try {
			crudServiceConstant.statement =  crudServiceConstant.connection.createStatement();
			crudServiceConstant.preparedStatementFind = crudServiceConstant.connection.prepareStatement(DaoProperties.FIND_COMPANY);
	    	crudServiceConstant.preparedStatementFindAll = crudServiceConstant.connection.prepareStatement(DaoProperties.FIND_ALL_COMPANIES);
			crudServiceConstant.preparedStatementFindByPage = crudServiceConstant.connection.prepareStatement(DaoProperties.PAGE_COMPANY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

    
	/**
	 * Find CRUD's operation
	 * @param pId
	 * @return company entity find with id gave in parameter
	 * @throws SQLException
	 */
    public Optional<Company> find(long id) {
    	company = null;
		try {
			crudServiceConstant.preparedStatementFind.setLong(1, id);
	    	resultSet = crudServiceConstant.preparedStatementFind.executeQuery();
	    	resultSet.next();
	    	System.out.println(resultSet);
    		company = MapperCompany.resultSetToEntity(resultSet).get();
    	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Optional.of(company);
    }
    
    
    
    /**
     * Retrieves all companies
     * @return list contains all companies
     * @throws Exception
     */
    public List<Company> findAll() {
    	companies = new ArrayList<>();
    	try {
			resultSet = crudServiceConstant.preparedStatementFind.executeQuery();
	    	while ( resultSet.next()){
	    		if (MapperCompany.resultSetToEntity(resultSet).isPresent()){
	    			company = MapperCompany.resultSetToEntity(resultSet).get();
		    		companies.add(company);
	    		}
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

    	return companies;
    }
   
    
    
    /**
     * Allows pagination for findAll companies
     * @param pOffset
     * @return list of companies paginated
     * @throws Exception
     */
    public List<Company> findByPage(long offset, int numberForEachpPage) {


    	companies = new ArrayList<>();
    	if (numberForEachpPage <= 0){
    		numberForEachpPage = crudServiceConstant.LIMIT_DEFAULT;
    	}
    	if (offset < 0){
    		offset = 0;;
    	}
    	try {
			crudServiceConstant.preparedStatementFindByPage.setInt(1, numberForEachpPage);
	    	crudServiceConstant.preparedStatementFindByPage.setLong(2, offset);
	    	resultSet = crudServiceConstant.preparedStatementFindByPage.executeQuery();
	      	while (resultSet.next()){
	    		if (MapperCompany.resultSetToEntity(resultSet).isPresent()){
	    			company = MapperCompany.resultSetToEntity(resultSet).get();
		    		companies.add(company);
	    		}
	      	}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

      	return companies;
      }
}