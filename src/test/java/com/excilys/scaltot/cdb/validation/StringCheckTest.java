package com.excilys.scaltot.cdb.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

/**
 * @author Caltot Stéphan
 *
 *         27 févr. 2017
 */
public class StringCheckTest {

    /**
     * Check good forms of isFormed function .
     */
    @Test
    public void isFormed() {
        assertTrue(StringValidator.isFormed(Optional.ofNullable(" t")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable(".")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable("-")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable("_")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable(" t")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable("  test")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable("-test")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable(" TEST")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable("_test")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable(".test")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable(" . _ - TESTtest")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable(" 05")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable("05_")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable("05test-+")));
        assertTrue(StringValidator.isFormed(Optional.ofNullable("  0 test1D_-. ")));
    }

    /**
     * Check wrong forms isFormed function .
     */
    @Test
    public void isNotFormed() {
        assertFalse(StringValidator.isFormed(Optional.ofNullable("")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("  ")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("*")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("&")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("'")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("é")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("à")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable(")")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("{")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("f5_-.*")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("test& @")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("&")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("@")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("ç ^")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("'  ")));
        assertFalse(StringValidator.isFormed(Optional.ofNullable("@")));
    }
}
