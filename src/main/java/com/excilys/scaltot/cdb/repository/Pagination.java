package com.excilys.scaltot.cdb.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.scaltot.cdb.managers.ComputerManagerBean;

/**
 * @author Caltot Stéphan
 *
 * 2 mars 2017
 */
public class Pagination {

    public static final Logger LOGGER = LoggerFactory.getLogger(ComputerManagerBean.class);

    private long pageSize = 10;
    private long currentPage = 0;
    private long numberOfElements = 0;
    private long offset = 0;
    private String filter = "";

    /**
     * Empty constructor.
     */
    public Pagination() {
        
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
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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
     * @param numberOfComputers : the numberOfComputers to set
     */
    public void setNumberOfElements(long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    /**
     * @return the offset
     */
    public long getOffset() {
        return offset;
    }

    /**
     * @param offset : the offset to set
     */
    public void setOffset(long offset) {
        this.offset = offset;
    }

    /**
     * @return the numberOfPages
     */
    public long getNumberOfPages() {
        return numberOfElements / pageSize;
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
        public PaginationBuilder withNumberOfElements(long numberOfElements){
            pagination.numberOfElements = numberOfElements;
            return this;
        }

        /**
         * Set builder parameter offset. 
         * @param offset : offset
         * @return paginationBuilder
         */
        public PaginationBuilder withOffset(long offset){
            pagination.offset = offset;
            return this;
        }

        /**
         * Set builder parameter currentPage. 
         * @param currentPage : currentPage
         * @return paginationBuilder
         */
        public PaginationBuilder withCurrentPage(long currentPage){
            pagination.currentPage = currentPage;
            return this;
        }

        /**
         * Set builder parameter pageSize. 
         * @param pageSize : pageSize
         * @return paginationBuilder
         */
        public PaginationBuilder withPageSize(long pageSize){
            pagination.pageSize = pageSize;
            return this;
        }

        /**
         * Set builder parameter filter. 
         * @param filter : filter
         * @return paginationBuilder
         */
        public PaginationBuilder withFilter(String filter){
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
