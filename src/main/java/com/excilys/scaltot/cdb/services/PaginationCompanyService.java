package com.excilys.scaltot.cdb.services;

import java.util.List;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.repository.Pagination;
import com.excilys.scaltot.cdb.repository.impl.CrudServiceCompanyImpl;

public abstract class PaginationCompanyService {
    
    public static void paginationInitialisation(Pagination paginationCompany) {
    	paginationCompany.setNumberOfElements(CrudCompanyService.getCountOfCompanies());
        paginationCompany.setNumberOfPages(paginationCompany.getNumberOfElements() / paginationCompany.getPageSize());
    }

	public static List<Company> findByPage(Pagination paginationCompany) {
		return CrudServiceCompanyImpl.INSTANCE.findByPageFilter(paginationCompany);
	}
	
	public static void nextPage(Pagination paginationCompany) {
		paginationCompany.nextPage();
	}
	
	public static void previousPage(Pagination paginationCompany) {
		paginationCompany.previousPage();
	}
	
	public static void setCurrentPage(Pagination paginationCompany, int numOfPage) {
		paginationCompany.setCurrentPage(numOfPage);
	}
	
	public static void setPageSize(Pagination paginationCompany, int pageSize) {
		paginationCompany.setPageSize(pageSize);
	}
	
	public static void setFilter(Pagination paginationCompany, String filter) {
		paginationCompany.setFilter(filter);
	}
}
