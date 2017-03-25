package com.excilys.scaltot.cdb.spring.mvc;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * @param parameters : parameters
     * @return page redirection
     */
    @GetMapping
    public String doGet(ModelMap model, @RequestParam MultiValueMap<String, String> parameters) {

        page = paginationServiceImpl.paginationInitialisation(new Pagination(), Computer.class);

        if (parameters.containsKey("filter")) {
            paginationServiceImpl.setFilter(page, parameters.get("filter").get(0));
        }

        if (parameters.containsKey("previousPage")) {
            long offset;
            long currentPage = Long.valueOf(parameters.get("numOfPage").get(0));
            long pageSize = Long.valueOf(parameters.get("size").get(0));

            currentPage = (currentPage) >= 0 ? (currentPage) : 0;
            offset = currentPage * pageSize;

            page.setCurrentPage(currentPage);
            page.setOffset(offset);

        } else if (parameters.containsKey("nextPage")) {

            long offset;
            long currentPage = Long.valueOf(parameters.get("numOfPage").get(0));
            long pageSize = Long.valueOf(parameters.get("size").get(0));
            long numberOfPages = page.getNumberOfPages();

            currentPage = (currentPage) <= numberOfPages ? (currentPage) : numberOfPages;
            offset = currentPage * pageSize;

            page.setCurrentPage(currentPage);
            page.setOffset(offset);

        } else {
            if (parameters.containsKey("size")) {
                paginationServiceImpl.setPageSize(page, Integer.valueOf(parameters.get("size").get(0)));
            }
            if (parameters.containsKey("numOfPage")) {
                paginationServiceImpl.setCurrentPage(page, Integer.valueOf(parameters.get("numOfPage").get(0)));
            }
        }

        computers = MapperComputerDto.computerListToComputerDto(paginationServiceImpl.findComputerByPage(page));

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
    public String doPost(ModelMap model,
            @RequestParam("selection") final Optional<String> selection) {

        if (selection.isPresent()) {
            String[] selections = selection.get().split(",");
            for (String computerId : selections) {
                LOGGER.info("DELETION ID" + Long.parseLong(computerId));
                crudComputerServiceImpl.delete(Long.parseLong(computerId));
            }
        }
        return "redirect:springcdb";
    }
}
