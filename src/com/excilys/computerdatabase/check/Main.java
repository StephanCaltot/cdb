package com.excilys.computerdatabase.check;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.entities.company.Company;
import com.excilys.computerdatabase.entities.computer.Computer;
import com.excilys.computerdatabase.repository.CrudServiceCompany;
import com.excilys.computerdatabase.repository.CrudServiceComputer;

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
		
//		List<Company> companies = new CrudServiceCompany().findByPage(0,-10);
//		
//		for ( Company comp : companies){
//			System.out.println(comp.toString());
//		}
//		
//		
//		System.out.println(StringCheck.isFormed("gfdgfdgfdgfd-fd-gd-f-df-fg-f_2035456_"));
		
		
//		Computer comp = new Computer.Builder().withName("test").build();
//		
//		comp.setDateWichIsDiscontinued(null);
//		System.out.println(comp.toString());
		
		
		Computer computer = new Computer.Builder().withId(45454).withName("mdrrrrrfff").build();
		System.out.println(computer.getName());
		new CrudServiceComputer().create(computer);
	}

}
