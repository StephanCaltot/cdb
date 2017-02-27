package com.excilys.scaltot.cdb.repository;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.mockito.Mockito;

import com.excilys.scaltot.cdb.entities.computer.Computer;

import org.junit.Test;

/**
 * @author Caltot Stéphan
 *
 *         27 févr. 2017
 */
public class MapperComputerTest {

    private ResultSet resultSet;

    /**
     * Initialization of result set.
     */
    @Before
    public void initBeforeEachTest() {
        resultSet = Mockito.mock(ResultSet.class);
    }

    /**
     * Check if the function return a computer entity from resultSet.
     *
     * @throws SQLException : SQL exception
     */
    @Test
    public void resultSetToEntityComputer() throws SQLException {
        Mockito.when(resultSet.getLong("id")).thenReturn((long) 1);
        Mockito.when(resultSet.getString("name")).thenReturn("computer test");
        Mockito.when(resultSet.getTimestamp("introduced")).thenReturn(Timestamp.valueOf("2010-01-01 00:00:00"));
        Mockito.when(resultSet.getTimestamp("discontinued")).thenReturn(Timestamp.valueOf("2012-01-01 00:00:00"));
        Mockito.when(resultSet.getLong("company_id")).thenReturn((long) 1);
        Mockito.when(resultSet.getString("company_name")).thenReturn("company test");

        Optional<Computer> optionalComputer = MapperComputer.resultSetToEntity(Optional.of(resultSet));
        if (optionalComputer.isPresent()) {
            Computer computer = optionalComputer.get();
            assertEquals(1, computer.getId());
            assertEquals("computer test", computer.getName());
            assertEquals(LocalDate.of(2010, 01, 01), computer.getDateWichIsIntroduced());
            assertEquals(LocalDate.of(2012, 01, 01), computer.getDateWichIsDiscontinued());
            assertEquals(1, computer.getManufacturer().getId());
        }
    }
}
