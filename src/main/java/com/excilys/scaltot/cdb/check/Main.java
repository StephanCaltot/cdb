package com.excilys.scaltot.cdb.check;

import java.sql.SQLException;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.repository.CrudServiceComputer;

/**
 * @author Caltot Stéphan
 *
 *         22 févr. 2017
 */
public class Main {

    /**
     * @param args :
     * @throws Exception :
     * @throws SQLException :
     */
    public static void main(String[] args) throws SQLException, Exception {

        // List<Company> companies = new CrudServiceCompany().findByPage(0,-10);
        //
        // for ( Company comp : companies){
        // System.out.println(comp.toString());
        // }
        //
        //
        // System.out.println(StringCheck.isFormed("gfdgfdgfdgfd-fd-gd-f-df-fg-f_2035456_"));

        // Computer comp = new Computer.Builder().withName("test").build();
        //
        // comp.setDateWichIsDiscontinued(null);
        // System.out.println(comp.toString());

        Computer computer = new CrudServiceComputer().find(0).get();
        System.out.println(computer.toString());

    }

}
