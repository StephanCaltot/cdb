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
        assertTrue(StringCheck.isFormed(Optional.ofNullable(" t")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable(".")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable("-")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable("_")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable(" t")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable("  test")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable("-test")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable(" TEST")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable("_test")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable(".test")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable(" . _ - TESTtest")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable(" 05")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable("05_")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable("05test-+")));
        assertTrue(StringCheck.isFormed(Optional.ofNullable("  0 test1D_-. ")));
    }

    /**
     * Check wrong forms isFormed function .
     */
    @Test
    public void isNotFormed() {
        assertFalse(StringCheck.isFormed(Optional.ofNullable("")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("  ")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("*")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("&")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("'")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("é")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("à")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable(")")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("{")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("f5_-.*")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("test& @")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("&")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("@")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("ç ^")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("'  ")));
        assertFalse(StringCheck.isFormed(Optional.ofNullable("@")));
    }
}
