package com.excilys.scaltot.cdb.webservices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Caltot Stéphan
 *
 * 11 avr. 2017
 */
@RestController
@RequestMapping(value="/")
public class ServiceComputer {

    @Resource
    private BookRepository bookRepository;

    @RequestMapping(value="/book/{id}", method=RequestMethod.GET)
    public Book getBook(@PathVariable Long id){
        return bookRepository.get(id);
    }

    @RequestMapping(value="/books", method=RequestMethod.GET)
    public List<Book> getBooks(){
        return bookRepository.getAll();
    }
}
