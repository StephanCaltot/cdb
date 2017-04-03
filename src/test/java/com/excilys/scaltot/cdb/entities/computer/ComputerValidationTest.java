package com.excilys.scaltot.cdb.entities.computer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.validation.ComputerValidator;

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
     * Check computer construction with wrong id.
     */
    @Test
    public void checkIfIsIdWrong() {
        computer = new Computer.ComputerBuilder().withId(-1).withName("computerTest")
                .withDateWichIsIntroduced(LocalDate.of(2001, 01, 01))
                .withDateWichIsDiscontinued(LocalDate.of(2011, 01, 01)).build();

        company = new Company.CompanyBuilder().withId(1).withName("companyTest").build();
        computer.setManufacturer(company);
        assertFalse(ComputerValidator.check(Optional.of(computer)));
    }

    /**
     * Check computer construction with wrong name.
     */
    @Test
    public void checkIfIsNamedWrong() {
        computer = new Computer.ComputerBuilder().withId(1).withName("computer*Test")
                .withDateWichIsIntroduced(LocalDate.of(2001, 01, 01))
                .withDateWichIsDiscontinued(LocalDate.of(2011, 01, 01)).build();

        company = new Company.CompanyBuilder().withId(1).withName("companyTest").build();
        computer.setManufacturer(company);
        assertFalse(ComputerValidator.check(Optional.of(computer)));

    }

    /**
     * Check computer construction with wrong introduced date.
     */
    @Test
    public void checkIfIsDatedWrong() {
        computer = new Computer.ComputerBuilder().withId(1).withName("computerTest")
                .withDateWichIsIntroduced(LocalDate.of(2012, 01, 01))
                .withDateWichIsDiscontinued(LocalDate.of(2011, 01, 01)).build();

        company = new Company.CompanyBuilder().withId(1).withName("companyTest").build();
        computer.setManufacturer(company);

        assertFalse(ComputerValidator.check(Optional.of(computer)));
    }

    /**
     * Check computer construction with wrong introduced date.
     */
    @Test
    public void checkIfIsCompanyIddWrong() {
        computer = new Computer.ComputerBuilder().withId(1).withName("computerTest")
                .withDateWichIsIntroduced(LocalDate.of(2010, 01, 01))
                .withDateWichIsDiscontinued(LocalDate.of(2011, 01, 01)).build();

        company = new Company.CompanyBuilder().withId(-1).withName("companyTest").build();
        computer.setManufacturer(company);

        assertFalse(ComputerValidator.check(Optional.of(computer)));
    }

    /**
     * Check computer construction with wrong company name.
     */
    @Test
    public void checkIfIsCompanyNameddWrong() {
        computer = new Computer.ComputerBuilder().withId(1).withName("computerTest")
                .withDateWichIsIntroduced(LocalDate.of(2010, 01, 01))
                .withDateWichIsDiscontinued(LocalDate.of(2011, 01, 01)).build();

        company = new Company.CompanyBuilder().withId(1).withName("company*Test").build();
        computer.setManufacturer(company);

        assertFalse(ComputerValidator.check(Optional.of(computer)));
    }
}
