package com.excilys.scaltot.cdb.services;

import java.util.List;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.repository.Pagination;
import com.excilys.scaltot.cdb.repository.impl.CrudServiceComputerImpl;

public abstract class PaginationComputerService {  
	
    public static void paginationInitialisation(Pagination paginationComputer) {
        paginationComputer.setNumberOfElements(CrudComputerService.getCountOfComputers());
        paginationComputer.setNumberOfPages(paginationComputer.getNumberOfElements() / paginationComputer.getPageSize());
    }

	public static List<Computer> findByPage(Pagination paginationComputer) {
		return CrudServiceComputerImpl.INSTANCE.findByPageFilter(paginationComputer);
	}

	public static void nextPage(Pagination paginationComputer) {
		paginationComputer.nextPage();
	}

	public static void previousPage(Pagination paginationComputer) {
		paginationComputer.previousPage();
	}

	public static void setCurrentPage(Pagination paginationComputer, int numOfPage) {
		paginationComputer.setCurrentPage(numOfPage);
	}
	
	public static void setPageSize(Pagination paginationComputer, int pageSize) {
		paginationComputer.setPageSize(pageSize);
	}
	
	public static void setFilter(Pagination paginationComputer, String filter) {
		paginationComputer.setFilter(filter);
	}
}
