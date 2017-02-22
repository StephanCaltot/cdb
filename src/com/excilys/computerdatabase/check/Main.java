package com.excilys.computerdatabase.check;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.entities.company.Company;
import com.excilys.computerdatabase.repository.CrudServiceCompany;

/**
 * @author Caltot Stéphan
 *
 * 22 févr. 2017
 */
public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException, Exception {
		
		List<Company> companies = new CrudServiceCompany().findByPage(10);
		
		for ( Company comp : companies){
			System.out.println(comp.toString());
		}
	}

}
