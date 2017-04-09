package com.excilys.scaltot.cdb.webapp.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.scaltot.cdb.dto.ComputerDto;
import com.excilys.scaltot.cdb.entities.Computer;
import com.excilys.scaltot.cdb.mappers.MapperComputerDto;
import com.excilys.scaltot.cdb.pagination.Pagination;
import com.excilys.scaltot.cdb.services.interfaces.CrudComputerService;
import com.excilys.scaltot.cdb.services.interfaces.PaginationService;


/**
 * @author Caltot Stéphan
 *
 * 23 mars 2017
 */
@Controller
@RequestMapping("/springcdb")
public class DashboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private PaginationService paginationServiceImpl;
    @Autowired
    private CrudComputerService crudComputerServiceImpl;
    private Pagination page;
    private List<ComputerDto> computers;

    /**
     * Initializes home page.
     * @param model : model
     * @param parameters : parameters
     * @return page redirection
     */
    @GetMapping
    public String doGet(ModelMap model, @RequestParam Map<String, String> parameters) {

        page = paginationServiceImpl.paginationInitialisation(new Pagination(), Computer.class);

        long currentPage = -10;

        if (parameters.containsKey("filter")) {
            paginationServiceImpl.setFilter(page, parameters.get("filter"));
        }
        if (parameters.containsKey("size")) {
            paginationServiceImpl.setPageSize(page, Integer.valueOf(parameters.get("size")));
        }
        if (parameters.containsKey("numOfPage")) {
            paginationServiceImpl.setCurrentPage(page, Integer.valueOf(parameters.get("numOfPage")));
        }

        if (parameters.containsKey("previousPage")) {
            long offset;
            currentPage = Long.valueOf(parameters.get("numOfPage"));
            long pageSize = Long.valueOf(parameters.get("size"));

            currentPage = (currentPage) >= 0 ? (currentPage) : 0;
            offset = currentPage * pageSize;

            page.setCurrentPage(currentPage);
            page.setOffset(offset);
            currentPage = 9999;


        } else if (parameters.containsKey("nextPage")) {

            long offset;
            currentPage = Long.valueOf(parameters.get("numOfPage"));
            long pageSize = Long.valueOf(parameters.get("size"));
            long numberOfPages = page.getNumberOfPages();

            currentPage = (currentPage) <= numberOfPages ? (currentPage) : numberOfPages;
            offset = currentPage * pageSize;

            page.setCurrentPage(currentPage);
            page.setOffset(offset);

        }

        computers = MapperComputerDto.computerListToComputerDto(paginationServiceImpl.findComputerByPage(page));

        LOGGER.debug("********************************************************************************");
        LOGGER.debug("Size of liste : " + computers.size());
        for (ComputerDto comp : computers) {
            LOGGER.debug("Computer : " + comp.toString());
        }


        model.addAttribute("computers", computers);
        model.addAttribute("numberOfPages", page.getNumberOfPages());
        model.addAttribute("currentPage", page.getCurrentPage());
        model.addAttribute("numberOfElements", page.getNumberOfElements());

        return "dashboard";
    }

    /**
     * Post delete method.
     * @param model : model
     * @param selection : selection
     * @return page redirection
     */
    @PostMapping
    public String doPost(ModelMap model, @RequestParam("selection") final Optional<String> selection) {

        if (selection.isPresent()) {
            String[] selections = selection.get().split(",");
            for (String computerId : selections) {
                LOGGER.info("DELETION ID" + Long.parseLong(computerId));
                crudComputerServiceImpl.delete(Long.parseLong(computerId));
            }
        }
        
//        redirectAttributes.addFlashAttribute("css", "success");
//        redirectAttributes.addFlashAttribute("msg", "User is deleted!");
        
        //return "redirect:/users";
        return "redirect:springcdb";
    }
}
