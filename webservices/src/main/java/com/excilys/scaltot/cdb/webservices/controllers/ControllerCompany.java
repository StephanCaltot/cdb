package com.excilys.scaltot.cdb.webservices.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Produces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.scaltot.cdb.dto.CompanyDto;
import com.excilys.scaltot.cdb.entities.Company;
import com.excilys.scaltot.cdb.mappers.MapperCompanyDto;
import com.excilys.scaltot.cdb.services.interfaces.CrudCompanyService;

/**
 * @author Caltot Stéphan
 *
 * 11 avr. 2017
 */
@RestController
@RequestMapping(value="/companies")
@Produces("application/json")
public class ControllerCompany {

    private CrudCompanyService crudCompanyService;

    /**
     * Constructor.
     */
    public ControllerCompany(CrudCompanyService crudCompanyService) {
        this.crudCompanyService = crudCompanyService;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<CompanyDto> getComputer(@PathVariable Long id) throws SQLException {
        Optional<Company> company = crudCompanyService.find(id);
        CompanyDto companyDto = MapperCompanyDto.companyToCompanyDto(company);
        return new ResponseEntity<CompanyDto>(companyDto,HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<CompanyDto>> getCompanies() {
        List<Company> companies = crudCompanyService.findAll();
        List<CompanyDto> companiesDto = MapperCompanyDto.companyListToCompanyDto(companies);
        return new ResponseEntity<List<CompanyDto>>(companiesDto,HttpStatus.OK);
    }
    
    
    @RequestMapping(method=RequestMethod.DELETE)
    public ResponseEntity<Long> putComputer(Long id) {
        crudCompanyService.delete(id);
        return new ResponseEntity<Long>( id, HttpStatus.OK);
    }

}
