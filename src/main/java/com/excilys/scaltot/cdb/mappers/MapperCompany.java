package com.excilys.scaltot.cdb.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.scaltot.cdb.entities.Company;

/**
 * @author Caltot Stéphan
 *
 *         23 févr. 2017
 */
public class MapperCompany implements RowMapper<Company> {

    private static Company company;

    /**
     * Transforms result retrieved in new company entity.
     *
     * @param resultSet : resultSet
     * @return Optional company entity
     */
    @Override
    public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        String name = null;
        long id = 0;

        company = new Company.CompanyBuilder().build();

        if (resultSet.getLong("id") != 0) {
            id = resultSet.getLong("id");
        }
        if (resultSet.getString("name") != null) {
            name = resultSet.getString("name");
        }
        company = new Company.CompanyBuilder().withName(name).withId(id).build();

        return company;
    }
}
