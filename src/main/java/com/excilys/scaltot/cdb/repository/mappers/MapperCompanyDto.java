package com.excilys.scaltot.cdb.repository.mappers;

import java.util.Optional;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.company.CompanyDto;

/**
 * @author Caltot Stéphan
 *
 * 3 mars 2017
 */
public class MapperCompanyDto {
    /**
     * Changes computer to computer DTO.
     * @param company : company
     * @return computerDto
     */
    public static CompanyDto computerToComputerDto(Optional<Company> company) {
        CompanyDto companyDto = new CompanyDto();

        companyDto.setId(company.get().getId());
        companyDto.setName(company.get().getName());

        return companyDto;
    };
}
