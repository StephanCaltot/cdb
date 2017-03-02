package com.excilys.scaltot.cdb.cli.mvc;

import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;

/**
 * @author Caltot Stéphan
 *
 * 1 mars 2017
 */
public class ViewCompany {

    public static final String FORMAT_COMPUTER = "%3s | %15.15s |%10s | %10s | %10s%n";

    public static final String FORMAT_COMPANY = "%3s | %15.15s%n";

    public static final String FOOTER = "                                      ";

    /**
     * Method displaying companies formated.
     *
     * @param companies
     *            :
     */
    public void displayAllCompanies(List<Company> companies) {
        System.out.println("\n");
        System.out.printf(FORMAT_COMPANY, "ID", "NAME");
        System.out.println("---------------------");
        for (Company company : companies) {
            System.out.printf(FORMAT_COMPANY, company.getId(), company.getName());
        }
        System.out.println("\n");
    }

    /**
     * Method displaying details for on computer.
     *
     * @param computer
     *            :
     */
    public void displayComputersDetails(Optional<Computer> computer) {
        if (computer.isPresent()) {
            System.out.println("\n" + computer.get().toString() + "\n");
        }
    }

    /**
     * Displaying information given in parameter.
     *
     * @param info
     *            :
     */
    public void displayInfo(Optional<String> info) {
        if (info.isPresent()) {
            System.out.print(info.get());
        }
    }
}
