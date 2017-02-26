package com.excilys.scaltot.cdb.validation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.repository.impl.CrudServiceComputerImpl;

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
     * @throws PersistenceException :
     */
    public static void main(String[] args) {

        Optional <Computer> computer = new CrudServiceComputerImpl().find(512);

        if (computer.isPresent()){
        	System.out.println(computer.toString());
        }
        computer.get().setDateWichIsDiscontinued(LocalDate.of(2030, 02, 27));
        computer.get().setDateWichIsIntroduced(LocalDate.of(2000, 11, 25));
        computer.get().setName("ipad de test");
        computer.get().setManufacturer(null);
        new CrudServiceComputerImpl().update(computer);
        Optional <Computer> comp = new CrudServiceComputerImpl().find(512);
        System.out.println(comp);

    }

}
