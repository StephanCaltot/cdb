package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import entities.Company;
import entities.Computer;
import interfaces.ICompany;
import interfaces.IComputer;


/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class main {

	public static void main(String[] args) throws Exception {
				
		
		Connection connection = JdbcConnection.getInstance().getConnection();
	
		ICompany company = new Company.CompanyBuilder("myBrandHtc").build();
		
		IComputer computer = new Computer.ComputerBuilder("myBrandHtc").build();
		
		CrudServiceComputer crudServiceComputer = new CrudServiceComputer();
		
		computer = new CrudServiceComputer().find(1);
	
		System.out.println(computer.display() + "\n");
		
		//System.out.println(computer.display());

		
//		computer.setName("alphonse");
//		crudServiceComputer.update(computer);
//		
		
//		ResultSet res =  stat.executeQuery(sql);
//		System.out.println(res.next());
//		System.out.println(res.getString("name"));


		JdbcConnection.getInstance().closeConnection();
	}

}
