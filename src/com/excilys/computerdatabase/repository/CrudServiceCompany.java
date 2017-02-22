package com.excilys.computerdatabase.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Logger;

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
	public CrudServiceCompany() throws SQLException{
		crudServiceConstant.statement =  crudServiceConstant.connection.createStatement();
	}
	
	

    
	/**
	 * Find CRUD's operation
	 * @param pId
	 * @return company entity find with id gave in parameter
	 * @throws SQLException
	 */
    public Company find(long id) throws Exception {
    	crudServiceConstant.preparedStatementFind = crudServiceConstant.connection.prepareStatement(DaoProperties.FIND_COMPANY);    
    	crudServiceConstant.preparedStatementFind.setLong(1, id);
    	resultSet = crudServiceConstant.preparedStatementFind.executeQuery();
    	resultSet.next();
    	Company company = resultSetToEntity();
    	return company;
 
    }
    
    
    
    /**
     * Retrieves all companies
     * @return list contains all companies
     * @throws Exception
     */
    public List<Company> findAll() throws Exception{
    	companies = new ArrayList<>();
    	crudServiceConstant.preparedStatementFind = crudServiceConstant.connection.prepareStatement(DaoProperties.FIND_ALL_COMPANIES);
    	resultSet = crudServiceConstant.preparedStatementFind.executeQuery();
    	while ( resultSet.next()){
    		company = resultSetToEntity();
    		companies.add(company);
    	}
    	return companies;
    }
   
    
    
    /**
     * Allows pagination for findAll companies
     * @param pOffset
     * @return list of companies paginated
     * @throws Exception
     */
    public List<Company> findByPage(long offset) throws Exception{
    	companies = new ArrayList<Company>();
    	crudServiceConstant.preparedStatementFindByPage = crudServiceConstant.connection.prepareStatement(DaoProperties.PAGE_COMPANY);
    	crudServiceConstant.preparedStatementFindByPage.setInt(1, crudServiceConstant.LIMIT);
    	crudServiceConstant.preparedStatementFindByPage.setLong(2, offset);
    	resultSet = crudServiceConstant.preparedStatementFindByPage.executeQuery();
      	while (resultSet.next()){
      		company = resultSetToEntity();
      		companies.add(company);
      	}
      	return companies;
      }
    
    
    
    /**
     * Transforms result retrieved in new company entity
     * @param pResultSet
     * @return company entity
     * @throws Exception
     */
    public Company resultSetToEntity() throws Exception{
        
    	String name = resultSet.getString("name");
    	company = new Company.Builder().withName(name).build();
    	company.setId(resultSet.getInt("id"));
    	
    	return company;
    }
}