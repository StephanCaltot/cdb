package com.excilys.computerdatabase.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.excilys.computerdatabase.entities.company.Company;

/**
 * @author Caltot Stéphan
 *
 * 23 févr. 2017
 */
public class MapperCompany {

	
    private static Company company;
    
    
    
    /**
     * Transforms result retrieved in new company entity
     * @param pResultSet
     * @return company entity
     * @throws Exception
     */
    public static Optional <Company> resultSetToEntity(ResultSet resultSet) {
        
    	String name;
		try {
			name = resultSet.getString("name");
	    	company = new Company.Builder().withName(name).build();
	    	company.setId(resultSet.getInt("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return Optional.of(company);
    }
	
}
