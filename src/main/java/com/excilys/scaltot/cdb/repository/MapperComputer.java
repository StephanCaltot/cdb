package com.excilys.scaltot.cdb.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;

/**
 * @author Caltot Stéphan
 *
 *         23 févr. 2017
 */
public class MapperComputer {

    private static Computer computer;

    /**
     * Method transform resultSet in computer entity.
     *
     * @param resultSet :
     * @return IComputer
     * @throws Exception
     */
    public static Optional<Computer> resultSetToEntity(ResultSet resultSet) {

        long id = 0;
        String name = null;
        Object introduced = null;
        Object discontinued = null;

        try {
            computer = new Computer.Builder().withName(name).build();

            if (resultSet.getLong("id") != 0) {
                id = resultSet.getLong("id");
                computer.setId(id);
            }
            if (resultSet.getString("name") != null) {
                name = resultSet.getString("name");
                computer.setName(name);
            }
            if (resultSet.getObject("introduced") != null) {
                introduced = ((Timestamp) resultSet.getObject("introduced")).toLocalDateTime().toLocalDate();
                computer.setDateWichIsIntroduced((LocalDate) introduced);
            }
            if (resultSet.getObject("discontinued") != null) {
                discontinued = ((Timestamp) resultSet.getObject("discontinued")).toLocalDateTime().toLocalDate();
                computer.setDateWichIsDiscontinued((LocalDate) discontinued);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (resultSet.getInt("company_id") != 0) {
                Optional<Company> company = new CrudServiceCompany().find(resultSet.getInt("company_id"));
                computer.setManufacturer(company.get());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.of(computer);
    }

}
