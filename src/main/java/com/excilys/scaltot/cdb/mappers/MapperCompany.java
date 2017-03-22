package com.excilys.scaltot.cdb.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;

/**
 * @author Caltot Stéphan
 *
 *         23 févr. 2017
 */
public class MapperCompany {

    private static Company company;

    /**
     * Transforms result retrieved in new company entity.
     *
     * @param resultSet : resultSet
     * @return Optional company entity
     */
    public static Optional<Company> resultSetToEntity(Optional<ResultSet> resultSet) {

        String name = null;
        long id = 0;

        try {
            company = new Company.CompanyBuilder().build();

            if (resultSet.get().getLong("id") != 0) {
                id = resultSet.get().getLong("id");
            }
            if (resultSet.get().getString("name") != null) {
                name = resultSet.get().getString("name");
            }
            company = new Company.CompanyBuilder().withName(name).withId(id).build();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return Optional.of(company);
    }

}
