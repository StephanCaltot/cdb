package com.excilys.scaltot.cdb.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.entities.computer.ComputerValidator;

/**
 * @author Caltot Stéphan
 *
 *         27 févr. 2017
 */
public class ComputerValidationTest {

    private Computer computer;
    private Company company;

    /**
     * Check computer construction.
     */
    @Test
    public void check() {
        computer = new Computer.ComputerBuilder().withId(1).withName("computerTest")
                .withDateWichIsIntroduced(LocalDate.of(2001, 01, 01))
                .withDateWichIsDiscontinued(LocalDate.of(2011, 01, 01)).build();

        assertTrue(ComputerValidator.check(Optional.of(computer)));

        company = new Company.CompanyBuilder().withId(1).withName("companyTest").build();
        computer.setManufacturer(company);
        assertTrue(ComputerValidator.check(Optional.of(computer)));
    }

    /**
     * Check computer construction.
     */
    @Test
    public void checkIfIsWrong() {
        computer = new Computer.ComputerBuilder().withId(-1).withName("computerTest")
                .withDateWichIsIntroduced(LocalDate.of(2001, 01, 01))
                .withDateWichIsDiscontinued(LocalDate.of(2011, 01, 01)).build();

        assertFalse(ComputerValidator.check(Optional.of(computer)));

        computer.setName("tes*t");
        assertFalse(ComputerValidator.check(Optional.of(computer)));

        computer.setDateWichIsIntroduced(LocalDate.of(2012, 01, 01));
        assertFalse(ComputerValidator.check(Optional.of(computer)));

        company = new Company.CompanyBuilder().withId(-1).withName("companyTest").build();
        computer.setManufacturer(company);
        assertFalse(ComputerValidator.check(Optional.of(computer)));

        computer.getManufacturer().setName("company*Test");
        assertFalse(ComputerValidator.check(Optional.of(computer)));
    }

}
