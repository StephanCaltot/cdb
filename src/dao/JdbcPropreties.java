package dao;
/**
 * Contains all constant 
 * @author screetts
 *
 */
public class JdbcPropreties {
	
	public static final String INSERT = "insert into company(name) values ( ? )";
	public static final String DELETE = "delete from computer where id= ?;";
	public static final String FIND   = "select * from company where id= ?;";	
	public static final String UPDATE = "update computer set name = ? where id = ?;";
	public static final String PAGE_COMPUTER = "update computer set name = ? where id = ?;";
	public static final String PAGE_COMPANY  = "update computer set name = ? where id = ?;";
}
