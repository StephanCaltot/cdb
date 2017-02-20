package dao;

import java.sql.Connection;
import java.sql.SQLException;

import entities.company.Company;
import entities.computer.Computer;
import interfaces.ICompany;
import interfaces.IComputer;


/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class main {

	public static void main(String[] args) throws Exception {
				
		
		//IComputer computer2 = new Computer.ComputerBuilder("f").build();
		//System.out.println(computer2.display());
		
		
		//Connection connection = JdbcConnection.getInstance().getConnection();
	
		//ICompany company = new Company.CompanyBuilder("myBrandHtc").build();
		
		IComputer computer = new Computer.ComputerBuilder("myBrandHtc").build();
		
		
		CrudServiceComputer crudServiceComputer = new CrudServiceComputer();
		crudServiceComputer.create(computer);
		
		//computer = crudServiceComputer.find(1);
		//System.out.println(computer.display());
		
		//computer.setName("bon sang");
		//crudServiceComputer.update(computer);
		
		/*
		computer = crudServiceComputer.find(577);
		System.out.println(computer.display());*/


		JdbcConnection.getInstance().closeConnection();
	}

}
