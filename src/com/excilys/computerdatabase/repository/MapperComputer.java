package com.excilys.computerdatabase.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import com.excilys.computerdatabase.entities.company.Company;
import com.excilys.computerdatabase.entities.computer.Computer;

/**
 * @author Caltot Stéphan
 *
 * 23 févr. 2017
 */
public class MapperComputer {

	
   private static Computer computer;
    
       
   
   /**
    * Method transform resultSet in computer entity
    * @param pResultSet
    * @return IComputer
    * @throws Exception
    */
   public static Optional<Computer> resultSetToEntity(ResultSet resultSet) {
   	
   	long id = 0;
   	String name = null;
   	Date introduced = null;
   	Date discontinued = null;
   	
   	try {
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
	} catch (SQLException e) {
		e.printStackTrace();
	}

   	computer = new Computer.Builder().withName(name).build();
	computer.setId(id);
   	computer.setDateWichIsDiscontinued(discontinued);
   	computer.setDateWichIsIntroduced(introduced);
   	try {
		if (resultSet.getInt("company_id") != 0 ) {
		   	Optional<Company> company = new CrudServiceCompany().find(resultSet.getInt("company_id"));
		   	computer.setManufacturer(company.get());	
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
   	
   	return Optional.of(computer);
   }

	
}
