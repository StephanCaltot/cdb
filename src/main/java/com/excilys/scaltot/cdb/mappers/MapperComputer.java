package com.excilys.scaltot.cdb.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.company.Company.CompanyBuilder;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.entities.computer.Computer.ComputerBuilder;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;

/**
 * @author Caltot Stéphan
 *
 *         23 févr. 2017
 */
public class MapperComputer {

    private static ComputerBuilder computerBuilder;
    private static CompanyBuilder companyBuilder;
    private static Computer computer;
    private static Company company;

    /**
     * Method transform resultSet in computer entity.
     *
     * @param resultSet : resultSet
     * @return Optional computer entity
     */
    public static Optional<Computer> resultSetToEntity(Optional<ResultSet> resultSet) {
        if (!resultSet.isPresent()) {
            return Optional.empty();
        }

        try {
            computerBuilder = new Computer.ComputerBuilder();
            companyBuilder = new Company.CompanyBuilder();

            if (resultSet.get().getLong("id") != 0) {
                long id = resultSet.get().getLong("id");
                computerBuilder.withId(id);
            }

            if (resultSet.get().getString("name") != null) {
                String name = resultSet.get().getString("name");
                computerBuilder.withName(name);
            }

            if (resultSet.get().getTimestamp(("introduced")) != null) {
                Timestamp introduced = resultSet.get().getTimestamp("introduced");
                computerBuilder.withDateWichIsIntroduced(introduced.toLocalDateTime().toLocalDate());
            }

            if (resultSet.get().getTimestamp(("discontinued")) != null) {
                Timestamp discontinued = resultSet.get().getTimestamp("discontinued");
                computerBuilder.withDateWichIsDiscontinued(discontinued.toLocalDateTime().toLocalDate());
            }

            if (resultSet.get().getLong("company_id") != 0) {
                long companyId = resultSet.get().getLong("company_id");
                companyBuilder.withId(companyId);
            }

            if (resultSet.get().getString("company_name") != null) {
                String companyName = resultSet.get().getString("company_name");
                companyBuilder.withName(companyName);
            }

            company = companyBuilder.build();
            computer = computerBuilder.withManufacturer(company).build();


        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return Optional.of(computer);
    }

}
