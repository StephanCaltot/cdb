package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.company.Company;
import interfaces.ICompany;


/**
 * Crud service allows CRUD's operations on Company entities 
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class CrudServiceCompany {

	
	
	/**
	 * Constructor initializing statement
	 * @throws SQLException
	 */
	public CrudServiceCompany() throws SQLException{
		crudServiceConstant.statement =  crudServiceConstant.connection.createStatement();
	}
	
	
	
	/**
	 * Create CRUD's operation
	 * @param pCompany
	 * @throws SQLException
	 */
    public void create(ICompany pCompany) throws SQLException {
    	crudServiceConstant.preparedStatementInsert = crudServiceConstant.connection.prepareStatement(DaoProperties.CREATE_COMPANY);
    	crudServiceConstant.preparedStatementInsert.setString(1, pCompany.getName());;
    	crudServiceConstant.preparedStatementInsert.execute();
    }

    
    
	/**
	 * Find CRUD's operation
	 * @param pId
	 * @return company entity find with id gave in parameter
	 * @throws SQLException
	 */
    public ICompany find(int pId) throws Exception {
    	crudServiceConstant.preparedStatementFind = crudServiceConstant.connection.prepareStatement(DaoProperties.FIND_COMPANY);    
    	crudServiceConstant.preparedStatementFind.setInt(1, pId);
    	crudServiceConstant.resultSet = crudServiceConstant.preparedStatementFind.executeQuery();
    	ICompany company = resultSetToEntity(crudServiceConstant.resultSet);
    	return company;
 
    }
    
    
    
    /**
     * Retrieves all companies
     * @return list contains all companies
     * @throws Exception
     */
    public List<ICompany> findAll() throws Exception{
    	crudServiceConstant.companies = new ArrayList<ICompany>();
		
    	crudServiceConstant.preparedStatementFind = crudServiceConstant.connection.prepareStatement(DaoProperties.FIND_ALL_COMPANIES);
    	crudServiceConstant.resultSet = crudServiceConstant.preparedStatementFind.executeQuery();
    	while ( crudServiceConstant.resultSet.next()){
    		crudServiceConstant.company = resultSetToEntity(crudServiceConstant.resultSet);
    		crudServiceConstant.companies.add(crudServiceConstant.company);
    	}
    	return crudServiceConstant.companies;
    }
   
    
    
    /**
     * Allows pagination for findAll companies
     * @param pOffset
     * @return list of companies paginated
     * @throws Exception
     */
    public List<ICompany> findByPage(int pOffset) throws Exception{
    	crudServiceConstant.companies = new ArrayList<ICompany>();
    	crudServiceConstant.preparedStatementFindByPage = crudServiceConstant.connection.prepareStatement(DaoProperties.PAGE_COMPANY);
    	crudServiceConstant.preparedStatementFindByPage.setInt(1, crudServiceConstant.LIMIT);
    	crudServiceConstant.preparedStatementFindByPage.setInt(2, pOffset);
    	crudServiceConstant.resultSet = crudServiceConstant.preparedStatementFindByPage.executeQuery();
      	while (crudServiceConstant.resultSet.next()){
      		crudServiceConstant.company = resultSetToEntity(crudServiceConstant.resultSet);
      		crudServiceConstant.companies.add(crudServiceConstant.company);
      	}
      	return crudServiceConstant.companies;
      }
    
    
    
    /**
     * Transforms result retrieved in new company entity
     * @param pResultSet
     * @return company entity
     * @throws Exception
     */
    public ICompany resultSetToEntity(ResultSet pResultSet) throws Exception{
    	crudServiceConstant.resultSet.next();
    	String name = crudServiceConstant.resultSet.getString("name");
    	ICompany company = new Company.CompanyBuilder(name).build();
    	company.setId(crudServiceConstant.resultSet.getInt("id"));
    	return company;
    }
}