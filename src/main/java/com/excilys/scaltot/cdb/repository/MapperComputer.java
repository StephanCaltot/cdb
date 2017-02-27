package com.excilys.scaltot.cdb.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;

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
     * @param resultSet
     *            :
     * @return Optional computer entity
     * @throws PersistenceException
     *             :
     * @throws Exception
     *             :
     */
    public static Optional<Computer> resultSetToEntity(Optional<ResultSet> resultSet) {
        if (!resultSet.isPresent()) {
            return Optional.empty();
        }
        long id = 0;
        long companyId = 0;
        String companyName = null;
        String name = null;
        Object introduced = null;
        Object discontinued = null;

        try {

            if (resultSet.get().getLong("id") != 0) {
                id = resultSet.get().getLong("id");
            }

            if (resultSet.get().getString("name") != null) {
                name = resultSet.get().getString("name");
            }

            if (resultSet.get().getObject("introduced") != null) {
                introduced = ((Timestamp) resultSet.get().getObject("introduced")).toLocalDateTime().toLocalDate();
            }

            if (resultSet.get().getObject("discontinued") != null) {
                discontinued = ((Timestamp) resultSet.get().getObject("discontinued")).toLocalDateTime().toLocalDate();
            }

            if (resultSet.get().getLong("company_id") != 0) {
                companyId = resultSet.get().getLong("company_id");
            }

            if (resultSet.get().getString("company_name") != null) {
                companyName = resultSet.get().getString("company_name");
            }

            Company company = new Company.Builder().withId(companyId).withName(companyName).build();

            computer = new Computer.Builder().withId(id).withName(name).withDateWichIsIntroduced((LocalDate) introduced)
                    .withDateWichIsDiscontinued((LocalDate) discontinued).withManufacturer(company).build();

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return Optional.of(computer);
    }

}
