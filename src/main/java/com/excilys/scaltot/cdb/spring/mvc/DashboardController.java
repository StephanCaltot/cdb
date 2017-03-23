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
import com.excilys.scaltot.cdb.servlets.ServletComputer;
import com.excilys.scaltot.cdb.utils.Pagination;


/**
 * @author Caltot Stéphan
 *
 * 23 mars 2017
 */
@Controller
@RequestMapping("/springcdb")
public class DashboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletComputer.class);

    @Autowired
    private PaginationService paginationServiceImpl;

    @Autowired
    private CrudComputerService crudComputerServiceImpl;

    private boolean init = false;
    
    private Pagination page;
    
    private List<ComputerDto> computers;

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model,
            @RequestParam(value = "action", defaultValue = "") final String action,
            @RequestParam(value = "pageSize", defaultValue = "10") final int size,
            @RequestParam(value = "numOfPage", defaultValue = "0") final int numOfPage,
            @RequestParam(value = "filter", defaultValue = "") final String filter) {

        if (!init) {
            page = paginationServiceImpl.paginationInitialisation(new Pagination(), Computer.class);
            init = true;
        }

        paginationServiceImpl.setFilter(page, filter);
        if (action.equals("")) {
            paginationServiceImpl.setCurrentPage(page, numOfPage);
        }
        paginationServiceImpl.setPageSize(page, size);


        if (action.equals("previousPage")) {
            long offset = page.getOffset();
            long currentPage = numOfPage;
            long pageSize = page.getPageSize();

            currentPage = (currentPage) >= 0 ? (currentPage) : 0;
            offset = currentPage * pageSize;

            page.setCurrentPage(currentPage);
            page.setOffset(offset);
            
            }
        if (action.equals("nextPage")) {
            long offset = page.getOffset();
            long currentPage = numOfPage;
            long pageSize = page.getPageSize();
            long numberOfPages = page.getNumberOfPages();

            currentPage = (currentPage) <= numberOfPages ? (currentPage) : numberOfPages;
            offset = currentPage * pageSize;

            page.setCurrentPage(currentPage);
            page.setOffset(offset);
        }


        computers = MapperComputerDto.computerListToComputerDto(paginationServiceImpl.findComputerByPage(page));
        model.addAttribute("computers", computers);
        model.addAttribute("filter", filter);
        model.addAttribute("numberOfPages", page.getNumberOfPages());
        model.addAttribute("currentPage", page.getCurrentPage());
        model.addAttribute("numberOfElements", page.getNumberOfElements());
        
        return "dashboard";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String doPost(ModelMap model,
            @RequestParam(value = "selection", defaultValue = "") final String selection) {

        String[] selections = selection.split(",");
        for (String computerId : selections) {
            LOGGER.info("DELETION ID" + Long.parseLong(computerId));
            crudComputerServiceImpl.delete(Long.parseLong(computerId));
        }
        return "dashboard";
    }
}
