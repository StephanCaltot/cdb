package com.excilys.scaltot.cdb.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.scaltot.cdb.entities.Company;
import com.excilys.scaltot.cdb.entities.Computer;
import com.excilys.scaltot.cdb.entities.Company.CompanyBuilder;
import com.excilys.scaltot.cdb.entities.Computer.ComputerBuilder;

/**
 * @author Caltot Stéphan
 *
 *         23 févr. 2017
 */
public class MapperComputer implements RowMapper<Computer> {

    private static ComputerBuilder computerBuilder;
    private static CompanyBuilder companyBuilder;
    private static Computer computer;
    private static Company company;

    /**
     * Method transform resultSet in computer entity for JDBC template.
     * @param resultSet :resultSet
     * @param rowNum : rowNum
     * @return computer
     * @throws SQLException
     */
    @Override
    public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        computerBuilder = new Computer.ComputerBuilder();
        companyBuilder = new Company.CompanyBuilder();

        if (resultSet.getLong("id") != 0) {
            long id = resultSet.getLong("id");
            computerBuilder.withId(id);
        }

        if (resultSet.getString("name") != null) {
            String name = resultSet.getString("name");
            computerBuilder.withName(name);
        }

        if (resultSet.getTimestamp(("introduced")) != null) {
            Timestamp introduced = resultSet.getTimestamp("introduced");
            computerBuilder.withDateWichIsIntroduced(introduced.toLocalDateTime().toLocalDate());
        }

        if (resultSet.getTimestamp(("discontinued")) != null) {
            Timestamp discontinued = resultSet.getTimestamp("discontinued");
            computerBuilder.withDateWichIsDiscontinued(discontinued.toLocalDateTime().toLocalDate());
        }

        if (resultSet.getLong("company_id") != 0) {
            long companyId = resultSet.getLong("company_id");
            companyBuilder.withId(companyId);
        }

        if (resultSet.getString("company_name") != null) {
            String companyName = resultSet.getString("company_name");
            companyBuilder.withName(companyName);
        }

        company = companyBuilder.build();
        computer = computerBuilder.withManufacturer(company).build();

        return computer;

    }
}
