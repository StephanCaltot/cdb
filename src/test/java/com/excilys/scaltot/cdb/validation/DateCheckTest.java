package com.excilys.scaltot.cdb.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;

/**
 * @author Caltot Stéphan
 *
 *         27 févr. 2017
 */
public class DateCheckTest {

    /**
     * Check good forms of isRealTime function .
     */
    @Test
    public void isRealTime() {
        assertTrue(
                DateCheck.isRealTime(Optional.of(LocalDate.of(2000, 01, 01)), Optional.of(LocalDate.of(2001, 01, 01))));
        assertTrue(
                DateCheck.isRealTime(Optional.of(LocalDate.of(2000, 01, 01)), Optional.of(LocalDate.of(2000, 02, 01))));
        assertTrue(
                DateCheck.isRealTime(Optional.of(LocalDate.of(2000, 01, 01)), Optional.of(LocalDate.of(2000, 01, 02))));
    }

    /**
     * Check wrong forms of isRealTime function .
     */
    @Test
    public void isNotRealTime() {
        assertFalse(
                DateCheck.isRealTime(Optional.of(LocalDate.of(2001, 01, 01)), Optional.of(LocalDate.of(2000, 01, 01))));
        assertFalse(
                DateCheck.isRealTime(Optional.of(LocalDate.of(2001, 02, 01)), Optional.of(LocalDate.of(2001, 01, 01))));
        assertFalse(
                DateCheck.isRealTime(Optional.of(LocalDate.of(2001, 01, 02)), Optional.of(LocalDate.of(2000, 01, 01))));
    }

    /**
     * Check food forms for date's format.
     */
    @Test
    public void isWellFormed() {
        assertTrue(DateCheck.formatIsValid(Optional.of("2012-01-01")));
    }

    /**
     * Check wrong forms for date's format.
     */
    @Test
    public void isWrongFormed() {
        assertFalse(DateCheck.formatIsValid(Optional.of("00-0000-00")));
        assertFalse(DateCheck.formatIsValid(Optional.of("00-00-0000")));
        assertFalse(DateCheck.formatIsValid(Optional.of("00-000-00")));
        assertFalse(DateCheck.formatIsValid(Optional.of("0000.0-00-00")));
        assertFalse(DateCheck.formatIsValid(Optional.of("00-000-00")));
        assertFalse(DateCheck.formatIsValid(Optional.empty()));
        assertFalse(DateCheck.formatIsValid(Optional.of("")));
        assertFalse(DateCheck.formatIsValid(Optional.of("yyyy-mm-dd")));
        assertFalse(DateCheck.formatIsValid(Optional.of("0000 - 00 - 00 ")));


    }
}
