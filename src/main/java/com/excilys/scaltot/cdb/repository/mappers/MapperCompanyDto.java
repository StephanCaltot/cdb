package com.excilys.scaltot.cdb.repository.mappers;

import java.util.ArrayList;
import java.util.List;
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
     * Changes company to company DTO.
     * @param company : company
     * @return computerDto
     */
    public static CompanyDto comanyToCompanyDto(Optional<Company> company) {
        CompanyDto companyDto = new CompanyDto();

        companyDto.setId(company.get().getId());
        companyDto.setName(company.get().getName());

        return companyDto;
    };

    /**
     * Changes companies list to company DTO.
     * @param companyList : companies
     * @return computerDto
     */
    public static List<CompanyDto> companyListToCompanyDto(List<Company> companies) {
        List<CompanyDto> companiesDto = new ArrayList<>();
        CompanyDto companyDto = new CompanyDto();

        for (Company company : companies) {
            companyDto.setId(company.getId());
            companyDto.setName(company.getName());
            companiesDto.add(companyDto);
        }

        return companiesDto;
    };
}
