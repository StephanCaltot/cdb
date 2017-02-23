package com.excilys.computerdatabase.repository;

import java.util.List;

/**
 * @author Caltot Stéphan
 *
 * 22 févr. 2017
 */
public interface CrudService<T> {
	
	T find(long id) throws Exception;
	
	List<T> findAll() throws Exception;
	
	List<T> findByPage(long offset) throws Exception;
	
}
