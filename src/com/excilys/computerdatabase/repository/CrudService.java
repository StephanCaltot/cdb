package com.excilys.computerdatabase.repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Caltot Stéphan
 *
 * 22 févr. 2017
 */
public interface CrudService<T> {
	
	public Optional<T> find(long id) throws Exception;
	
	public List<T> findAll() throws Exception;
	
	public List<T> findByPage(long offset, int numberForEachpPage) throws Exception;
	
}
