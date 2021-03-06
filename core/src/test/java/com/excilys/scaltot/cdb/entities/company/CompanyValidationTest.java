package com.excilys.scaltot.cdb.entities.company;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.excilys.scaltot.cdb.entities.Company;
import com.excilys.scaltot.cdb.validation.CompanyValidator;

/**
 * @author Caltot Stéphan
 *
 *         27 févr. 2017
 */
public class CompanyValidationTest {
    private Company company;

    /**
     * Check computer construction.
     */
    @Test
    public void check() {
        company = new Company.CompanyBuilder().withId(1).withName("computerTest").build();

        assertTrue(CompanyValidator.check(Optional.of(company)));
    }

    /**
     * Check computer construction with wrong id.
     */
    @Test
    public void checkIfIsIdWrong() {
        company = new Company.CompanyBuilder().withId(-1).withName("computerTest").build();

        assertFalse(CompanyValidator.check(Optional.of(company)));
    }

    /**
     * Check computer construction with wrong name.
     */
    @Test
    public void checkIfIsNamedWrong() {
        company = new Company.CompanyBuilder().withId(1).withName("computer*Test").build();

        assertFalse(CompanyValidator.check(Optional.of(company)));
    }
}
