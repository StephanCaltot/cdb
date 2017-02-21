package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import entities.computer.Computer;
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
		List<String> computers = crudServiceComputer.findAll();
		for(String computerr : computers)
			System.out.println( computerr.toString());


		JdbcConnection.getInstance().closeConnection();
	}

}
