package com.excilys.scaltot.cdb.spring.mvc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.entities.computer.ComputerDto;
import com.excilys.scaltot.cdb.mappers.MapperComputerDto;
import com.excilys.scaltot.cdb.services.interfaces.CrudComputerService;
import com.excilys.scaltot.cdb.services.interfaces.PaginationService;
import com.excilys.scaltot.cdb.utils.Pagination;


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
     * @param action :action
     * @param size : size
     * @param numOfPage : numOfPage
     * @param filter : filter
     * @return page redirection
     */
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model,
            @RequestParam(value = "action", defaultValue = "") final String action,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "numOfPage", defaultValue = "0") final int numOfPage,
            @RequestParam(value = "filter", defaultValue = "") final String filter) {

        page = paginationServiceImpl.paginationInitialisation(new Pagination(), Computer.class);
        paginationServiceImpl.setFilter(page, filter);

        if (action.equals("previousPage")) {
            long offset;
            long currentPage = numOfPage;
            long pageSize = size;

            currentPage = (currentPage) >= 0 ? (currentPage) : 0;
            offset = currentPage * pageSize;

            page.setCurrentPage(currentPage);
            page.setOffset(offset);

        } else if (action.equals("nextPage")) {
            long offset;
            long currentPage = numOfPage;
            long pageSize = size;
            long numberOfPages = page.getNumberOfPages();

            currentPage = (currentPage) <= numberOfPages ? (currentPage) : numberOfPages;
            offset = currentPage * pageSize;

            page.setCurrentPage(currentPage);
            page.setOffset(offset);
        } else {
            paginationServiceImpl.setPageSize(page, size);
            paginationServiceImpl.setCurrentPage(page, numOfPage);
        }

        computers = MapperComputerDto.computerListToComputerDto(paginationServiceImpl.findComputerByPage(page));
        model.addAttribute("computers", computers);
        model.addAttribute("filter", filter);
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
    @RequestMapping(method = RequestMethod.POST)
    public String doPost(ModelMap model,
            @RequestParam(value = "selection", defaultValue = "") final String selection) {

        String[] selections = selection.split(",");
        for (String computerId : selections) {
            LOGGER.info("DELETION ID" + Long.parseLong(computerId));
            crudComputerServiceImpl.delete(Long.parseLong(computerId));
        }
        return "redirect:springcdb";
    }
}
