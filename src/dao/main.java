package dao;

import java.sql.Connection;
import java.sql.SQLException;
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
		computer = crudServiceComputer.find(577);
		System.out.println(computer.display());
		
		computer.setName("bon sang");
		crudServiceComputer.update(computer);
		
		
		computer = crudServiceComputer.find(577);
		System.out.println(computer.display());
		
//		ResultSet res =  stat.executeQuery(sql);
//		System.out.println(res.next());
//		System.out.println(res.getString("name"));


		JdbcConnection.getInstance().closeConnection();
	}

}
