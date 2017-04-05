package com.excilys.scaltot.cdb.entities.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

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
     * @param companies : companies
     * @return computerDto
     */
    public static List<CompanyDto> companyListToCompanyDto(List<Company> companies) {
        List<CompanyDto> companiesDto = new ArrayList<>();
        CompanyDto companyDto;

        for (Company company : companies) {
            companyDto = new CompanyDto();
            companyDto.setId(company.getId());
            companyDto.setName(company.getName());
            companiesDto.add(companyDto);
        }

        return companiesDto;
    };

    /**
     * Changes companyDto to company.
     * @param companyDto : companyDto
     * @return company
     */
    public static Company companyDtoToCompany(CompanyDto companyDto) {

        Company company = null;

        if (StringUtils.isNotBlank(companyDto.getName()) && companyDto.getId() > 0) {
            company = new Company.CompanyBuilder()
                    .withId(companyDto.getId())
                    .withName(companyDto.getName())
                    .build();
        }

        return company;
    }
}
