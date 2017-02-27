package com.excilys.scaltot.cdb.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.company.CompanyValidator;

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
     * Check computer construction.
     */
    @Test
    public void checkIfIsWrong() {
        company = new Company.CompanyBuilder().withId(-1).withName("comput*erTest").build();

        assertFalse(CompanyValidator.check(Optional.of(company)));
    }
}
