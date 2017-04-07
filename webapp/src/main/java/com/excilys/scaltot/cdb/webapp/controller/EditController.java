package com.excilys.scaltot.cdb.webapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excilys.scaltot.cdb.dto.CompanyDto;
import com.excilys.scaltot.cdb.dto.ComputerDto;
import com.excilys.scaltot.cdb.entities.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.mappers.MapperCompanyDto;
import com.excilys.scaltot.cdb.mappers.MapperComputerDto;
import com.excilys.scaltot.cdb.services.interfaces.CrudCompanyService;
import com.excilys.scaltot.cdb.services.interfaces.CrudComputerService;
import com.excilys.scaltot.cdb.webapp.validator.ComputerFormValidator;

/**
 * @author Caltot Stéphan
 *
 * 23 mars 2017
 */
@Controller
@RequestMapping("/editComputer")
public class EditController {

    @Autowired
    private CrudComputerService crudComputerServiceImpl;

    @Autowired
    private CrudCompanyService crudCompanyServiceImpl;
    private List<CompanyDto> companies;
    private ComputerDto computerDto;

    @Autowired
    private ComputerFormValidator computerFormValidator;

    /**
     * Set the validator.
     * @param binder : binder
     */
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(computerFormValidator);
    }

    /**
     * Set computer properties on the edit page.
     * @param model : model
     * @param id : id
     * @return page redirection
     */
    @GetMapping
    public String doGet(ModelMap model, @RequestParam(value = "id", defaultValue = "0") final int id) {

      companies = MapperCompanyDto.companyListToCompanyDto(crudCompanyServiceImpl.findAll());
      Optional<Computer> computer = crudComputerServiceImpl.find(id);
      computerDto = MapperComputerDto.computerToComputerDto(computer);
      model.addAttribute("companies", companies);
      model.addAttribute("computerDto", computerDto);

      return "editComputer";
    }

    /**
     * Post method for edit.
     * @param model : model
     * @param computerDto : computer
     * @param result : result
     * @param redirectAttributes redirectAttributes
     * @return page redirection
     */
    @PostMapping
    public String doPost(@ModelAttribute("computerDto") @Validated ComputerDto computerDto,
            BindingResult result,
            Model model,
            final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            companies = MapperCompanyDto.companyListToCompanyDto(crudCompanyServiceImpl.findAll());
            model.addAttribute("companies", companies);
            model.addAttribute("computerDto", computerDto);

            return "editComputer";
        } else {

            try {
                crudComputerServiceImpl.update(Optional.ofNullable(MapperComputerDto.computerDtoToComputer(computerDto)));
            } catch (PersistenceException persistenceException) {

            }
        }


        return "redirect:springcdb";
    }
}
