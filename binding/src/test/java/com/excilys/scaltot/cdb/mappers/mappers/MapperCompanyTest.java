package com.excilys.scaltot.cdb.mappers.mappers;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.scaltot.cdb.entities.Company;
import com.excilys.scaltot.cdb.mappers.MapperCompany;

/**
 * @author Caltot Stéphan
 *
 * 27 févr. 2017
 */
public class MapperCompanyTest {

    private ResultSet resultSet;

    /**
     * Initialization of result set.
     */
    @Before
    public void initBeforeEachTest() {
        resultSet = Mockito.mock(ResultSet.class);
    }

    /**
     * Check if the function return a company entity from resultSet.
     *
     * @throws SQLException : SQL exception
     */
    @Test
    public void resultSetToEntityCompany() throws SQLException {
        Mockito.when(resultSet.getLong("id")).thenReturn((long) 1);
        Mockito.when(resultSet.getString("name")).thenReturn("company test");

        Company mapperCompany = new MapperCompany().mapRow(resultSet, Integer.MIN_VALUE);
            Company company = mapperCompany;
            assertEquals(1, company.getId());
            assertEquals("company test", company.getName());
    }
}
