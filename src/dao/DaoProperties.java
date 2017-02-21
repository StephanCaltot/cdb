package dao;

/**
 * @author Caltot Stéphan
 *
 * 21 févr. 2017
 */
public class DaoProperties {

	public static final String CREATE_COMPUTER    = "insert into computer(name) values ( ? )";
	public static final String DELETE_COMPUTER    = "delete from computer where id= ?;";
	public static final String UPDATE_COMPUTER    = "update computer set name = ? where id = ?;";
	public static final String CREATE_COMPANY     = "insert into company(name) values ( ? )";
	public static final String DELETE_COMPANY     = "delete from company where id= ?;";
	public static final String UPDATE_COMPANY     = "update company set name = ? where id = ?;";
	public static final String FIND_COMPUTER      = "select * from computer where id= ?;";
	public static final String FIND_COMPANY       = "select * from company where id= ?;";
	public static final String FIND_ALL_COMPUTERS = "select * from computer limit ? offset ?;";
	public static final String FIND_ALL_COMPANIES = "select * from company limit ? offset ?;";
	public static final String PAGE_COMPUTER	  = "select * from computer limit ? offset ?;";
	public static final String PAGE_COMPANY 	  = "select * from company limit ? offset ?;";
	
}
