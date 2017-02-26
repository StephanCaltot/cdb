package com.excilys.scaltot.cdb.utils;

/**
 * Class contains all needed constant for dao request.
 *
 * @author Caltot Stéphan
 *
 *         21 févr. 2017
 */
public class DaoProperties {

    public static final String CREATE_COMPUTER = "insert into computer(name, introduced, discontinued, company_id) values ( ?, ?, ?, ? )";
    public static final String DELETE_COMPUTER = "delete from computer where id= ?;";
    public static final String UPDATE_COMPUTER = "update computer set name = ?, introduced = ?, discontinued = ?, company_id = ? where id = ?;";
    public static final String CREATE_COMPANY = "insert into company(name) values ( ? )";
    public static final String DELETE_COMPANY = "delete from company where id= ?;";
    public static final String UPDATE_COMPANY = "update company set name = ? where id = ?;";
    public static final String FIND_COMPUTER = "select computer.id, computer.name, computer.introduced, computer.discontinued,"
    		+ " computer.company_id, company.name company_nam"
    		+ "e from computer left join company on company.id = computer.company_id where computer.id = ?;";
    public static final String FIND_COMPANY = "select id, name from company where id= ?;";
    public static final String FIND_ALL_COMPUTERS = "select id, name, introduced, discontinued, company_id from computer limit ? offset ?;";
    public static final String FIND_ALL_COMPANIES = "select id, name from company limit ? offset ?;";
    public static final String PAGE_COMPUTER = "select computer.id, computer.name, computer.introduced, computer.discontinued,"
    		+ " computer.company_id, company.name company_name from computer "
    		+ "left join company on company.id = computer.company_id limit ? offset ?;";
    public static final String PAGE_COMPANY = "select id, name from company limit ? offset ?;";

}
