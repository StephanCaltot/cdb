package com.excilys.scaltot.cdb.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.repository.mappers.MapperCompany;
import com.excilys.scaltot.cdb.repository.mappers.MapperComputer;
import com.excilys.scaltot.cdb.services.CrudComputerService;
import com.excilys.scaltot.cdb.utils.DaoProperties;
import com.excilys.scaltot.cdb.utils.JdbcConnection;




/**
 * @author Caltot Stéphan
 *
 * 2 mars 2017
 */
public class Pagination<E> {

    public static final Logger LOGGER = LoggerFactory.getLogger(Pagination.class);

    private long pageSize = 10;
    private long currentPage = 0;
    private long numberOfElements;
    private long numberOfPages;
    private long offset = 0;
    private String filter = "";
    private List<E> elements;
    private ResultSet resultSet;
    private E e;
    private Connection connection;
    private JdbcConnection jdbcConnection = JdbcConnection.INSTANCE;

    /**
     * Empty constructor.
     */
    public Pagination() {
        this.numberOfElements = CrudComputerService.getCountOfComputers();
        this.numberOfPages = this.numberOfElements / this.pageSize;
    }

    /**
     * @return the pageSize
     */
    public long getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize : the pageSize to set
     */
    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
        this.currentPage = 0;
        this.numberOfPages = this.numberOfElements / this.pageSize;
    }

    /**
     * @return the currentPage
     */
    public long getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage : the currentPage to set
     */
    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
        this.offset = currentPage * pageSize;
    }

    /**
     * @return the filter
     */
    public String getFilter() {
        return filter;
    }

    /**
     * @param filter : the filter to set
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * @return the numberOfComputers
     */
    public long getNumberOfElements() {
        return numberOfElements;
    }

    /**
     * Set number of elements .
     * @param numberOfElements : the numberOfComputers to set
     */
    public void setNumberOfElements(long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    /**
     * @return the offset
     */
    public long getOffset() {
        return currentPage * pageSize;
    }

    /**
     * @param offset : the offset to set
     */
    public void setOffset(long offset) {
        this.offset = offset;
    }

    /**
     * @param numberOfPages : the numberOfPages to set
     */
    public void setNumberOfPages(long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * @return the numberOfPages
     */
    public long getNumberOfPages() {
        this.numberOfPages = numberOfElements / pageSize;
        return numberOfPages;
    }

    /**
    * Switch to previous page.
    * @return list of Computers
    */
   public List<E> previousPage() {
       this.currentPage = (currentPage - 1) >= 0 ? (currentPage - 1) : 0;
       this.offset = currentPage * pageSize;
       this.elements = (List<E>) findByPageFilter();
       return elements;
   }

   /**
    * Switch to next page.
    * @return list of Computers
    */
   public List<E> nextPage() {
       this.currentPage = (currentPage + 1) <= numberOfPages ? (currentPage + 1) : numberOfPages;
       this.offset = currentPage * pageSize;
       this.elements = (List<E>) findByPageFilter();
       return elements;
   }

   
   /**
    * Retrieves computers paginated by limit ( 10 here ).
    *
    * @return list of computers paginated
    * @throws PersistenceException : PersistenceException
    * @throws Exception
    */
   public List<E> findByPageFilter() {

       connection = jdbcConnection.getConnection();
       elements = new ArrayList<E>();

       if (this.pageSize <= 0) {
           this.pageSize = CrudServiceConstant.LIMIT_DEFAULT;
       }
       if (this.offset < 0) {
           this.offset = 0;
       }
       try {
           CrudServiceConstant.preparedStatementFindByPage = connection.prepareStatement(DaoProperties.PAGE_COMPUTER_FILTERED);
           CrudServiceConstant.preparedStatementFindByPage.setString(1, "%" + filter + "%");
           CrudServiceConstant.preparedStatementFindByPage.setLong(2, pageSize);
           CrudServiceConstant.preparedStatementFindByPage.setLong(3, offset);
           resultSet = CrudServiceConstant.preparedStatementFindByPage.executeQuery();

           while (resultSet.next()) {

               if (MapperComputer.resultSetToEntity(Optional.of(resultSet)).isPresent()) {
                   e = (E) MapperComputer.resultSetToEntity(Optional.of(resultSet)).get();
                   elements.add(e);
               }
           }

           jdbcConnection.commit();
       } catch (SQLException e) {
           jdbcConnection.rollback();
           throw new PersistenceException(e);
       } finally {
           CrudServiceConstant.jdbcConnection.closeConnection();
       }

       return elements;
   }

   /**
    * Retrieves companies paginated by limit ( 10 here ).
    *
    * @return list of companies paginated
    * @throws PersistenceException : PersistenceException
    * @throws Exception
    */
   public List<E> findByPageFilterCompany() {

       connection = jdbcConnection.getConnection();
       elements = new ArrayList<E>();

       if (this.pageSize <= 0) {
           this.pageSize = CrudServiceConstant.LIMIT_DEFAULT;
       }
       if (this.offset < 0) {
           this.offset = 0;
       }
       try {
           CrudServiceConstant.preparedStatementFindByPage = connection.prepareStatement(DaoProperties.PAGE_COMPANY_FILTERED);
           CrudServiceConstant.preparedStatementFindByPage.setString(1, "%" + filter + "%");
           CrudServiceConstant.preparedStatementFindByPage.setLong(2, pageSize);
           CrudServiceConstant.preparedStatementFindByPage.setLong(3, offset);
           resultSet = CrudServiceConstant.preparedStatementFindByPage.executeQuery();

           while (resultSet.next()) {

               if (MapperCompany.resultSetToEntity(Optional.of(resultSet)).isPresent()) {
                   e = (E) MapperCompany.resultSetToEntity(Optional.of(resultSet)).get();
                   elements.add(e);
               }
           }

           jdbcConnection.commit();
       } catch (SQLException e) {
           jdbcConnection.rollback();
           throw new PersistenceException(e);
       } finally {
           CrudServiceConstant.jdbcConnection.closeConnection();
       }

       return elements;
   }

    /**
     * Builder Pattern for pagination.
     *
     * @author screetts
     */
    public static class PaginationBuilder {

        private Pagination pagination;

        /**
         * Builder constructor.
         */
        public PaginationBuilder() {
            this.pagination = new Pagination();
        }

        /**
         * Set builder parameter numberOfElements.
         * @param numberOfElements : numberOfElements
         * @return paginationBuilder
         */
        public PaginationBuilder withNumberOfElements(long numberOfElements) {
            pagination.numberOfElements = numberOfElements;
            return this;
        }

        /**
         * Set builder parameter offset.
         * @param offset : offset
         * @return paginationBuilder
         */
        public PaginationBuilder withOffset(long offset) {
            pagination.offset = offset;
            return this;
        }

        /**
         * Set builder parameter currentPage.
         * @param currentPage : currentPage
         * @return paginationBuilder
         */
        public PaginationBuilder withCurrentPage(long currentPage) {
            pagination.currentPage = currentPage;
            return this;
        }

        /**
         * Set builder parameter pageSize.
         * @param pageSize : pageSize
         * @return paginationBuilder
         */
        public PaginationBuilder withPageSize(long pageSize) {
            pagination.pageSize = pageSize;
            return this;
        }

        /**
         * Set builder parameter filter.
         * @param filter : filter
         * @return paginationBuilder
         */
        public PaginationBuilder withFilter(String filter) {
            pagination.filter = filter;
            return this;
        }

        /**
         * Build pagination.
         * @return Pagination
         */
        public Pagination build() {

            return pagination;

        }
    }
    
}
