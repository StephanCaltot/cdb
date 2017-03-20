package com.excilys.scaltot.cdb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;

/**
 * Class contains all needed constant for crudService operation.
 *
 * @author screetts
 *
 */
public abstract class CrudServiceConstant {

    /**
     * Default Limit of pagination.
     */
    public static final int LIMIT_DEFAULT = 10;

    public static Statement statement;
    public static ResultSet resultSet;

    public static PreparedStatement preparedStatementInsert;
    public static PreparedStatement preparedStatementFind;
    public static PreparedStatement preparedStatementDelete;
    public static PreparedStatement preparedStatementUpdate;
    public static PreparedStatement preparedStatementFindAll;
    public static PreparedStatement preparedStatementFindByPage;
    public static PreparedStatement preparedStatementCountComputer;
    public static PreparedStatement preparedStatementCountCompanies;
    public static PreparedStatement preparedStatementComputersFiltered;
    public static PreparedStatement preparedStatementCompaniesFiltered;

    public static List<Computer> computers;
    public static List<Company> companies;

}
