package com.excilys.scaltot.cdb.cli.mvc.company;
//package com.excilys.scaltot.cdb.cli.mvc;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Scanner;
//
//import com.excilys.scaltot.cdb.cli.ViewCli;
//import com.excilys.scaltot.cdb.entities.company.Company;
//import com.excilys.scaltot.cdb.exceptions.PersistenceException;
//import com.excilys.scaltot.cdb.services.CrudCompanyService;
//import com.excilys.scaltot.cdb.repository.Pagination;
//
///**
// * @author Caltot Stéphan
// *
// * 1 mars 2017
// */
//public class ControllerCompany {
//
//    private static ViewCompany viewCli;
//    private static List<Company> companies;
//    private static long numberForEachpPage = 10;
//    private static long offsetCompany = 0;
//    private static Scanner scan;
//
//    /**
//     * Method : listing companies paginated.
//     *
//     * @throws Exception :
//     * @throws PersistenceException :
//     */
//    public static void listingOfCompanies() {
//
//        String choice = null;
//
//        companies = CrudCompanyService.findByPage();
//        viewCli.displayAllCompanies(companies);
//        viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
//        do {
//
//            viewCli.displayInfo(Optional.of("\nPress (p) to preview and (n) to next : "));
//            choice = scan.nextLine();
//
//            switch (choice) {
//            case "1":
//                //listingOfComputers();
//                break;
//            case "2":
//                listingOfCompanies();
//                break;
//            case "3":
//                //showComputersDetails();
//                break;
//            case "6":
//                //deleteComputer();
//                break;
//            case "d":
//                //viewCli.displayMenu();
//                break;
//            case "n":
//                offsetCompany += 10;
//                companies = CrudCompanyService.findByPage(Pagination );
//                viewCli.displayAllCompanies(companies);
//                viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
//                break;
//            case "p":
//                if (offsetCompany - 10 >= 0) {
//                    offsetCompany -= 10;
//                    companies = CrudCompanyService.findByPage(offsetCompany, numberForEachpPage);
//                    viewCli.displayAllCompanies(companies);
//                } else {
//                    viewCli.displayInfo(Optional.of("Index is too low you have to 'next'"));
//                }
//                break;
//            case "q":
//                offsetCompany = -1;
//                break;
//            default:
////                viewCli.displayInfo(Optional.of("You have to press (n) or (p) or (q) to quit"));
//                break;
//            }
//
//        } while (offsetCompany >= 0);
//
//    }
//}
