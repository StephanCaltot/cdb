package com.excilys.scaltot.cdb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Caltot Stéphan
 *
 *         2 mars 2017
 */
public class Pagination {

    public static final Logger LOGGER = LoggerFactory.getLogger(Pagination.class);

    private long pageSize = 10;
    private long currentPage = 0;
    private long numberOfElements;
    private long numberOfPages;
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
     * @param pageSize
     *            : the pageSize to set
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
     * @param currentPage
     *            : the currentPage to set
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
     * @param filter
     *            : the filter to set
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
     *
     * @param numberOfElements
     *            : the numberOfComputers to set
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
     * @param offset
     *            : the offset to set
     */
    public void setOffset(long offset) {
        this.offset = offset;
    }

    /**
     * @param numberOfPages
     *            : the numberOfPages to set
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
     */
    public void previousPage() {
        this.currentPage = (currentPage - 1) >= 0 ? (currentPage - 1) : 0;
        this.offset = currentPage * pageSize;
    }

    /**
     * Switch to next page.
     */
    public void nextPage() {
        this.currentPage = (currentPage + 1) <= numberOfPages ? (currentPage + 1) : numberOfPages;
        this.offset = currentPage * pageSize;
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
         *
         * @param numberOfElements
         *            : numberOfElements
         * @return paginationBuilder
         */
        public PaginationBuilder withNumberOfElements(long numberOfElements) {
            pagination.numberOfElements = numberOfElements;
            return this;
        }

        /**
         * Set builder parameter offset.
         *
         * @param offset
         *            : offset
         * @return paginationBuilder
         */
        public PaginationBuilder withOffset(long offset) {
            pagination.offset = offset;
            return this;
        }

        /**
         * Set builder parameter currentPage.
         *
         * @param currentPage
         *            : currentPage
         * @return paginationBuilder
         */
        public PaginationBuilder withCurrentPage(long currentPage) {
            pagination.currentPage = currentPage;
            return this;
        }

        /**
         * Set builder parameter pageSize.
         *
         * @param pageSize
         *            : pageSize
         * @return paginationBuilder
         */
        public PaginationBuilder withPageSize(long pageSize) {
            pagination.pageSize = pageSize;
            return this;
        }

        /**
         * Set builder parameter filter.
         *
         * @param filter
         *            : filter
         * @return paginationBuilder
         */
        public PaginationBuilder withFilter(String filter) {
            pagination.filter = filter;
            return this;
        }

        /**
         * Build pagination.
         *
         * @return Pagination
         */
        public Pagination build() {

            return pagination;

        }
    }

}
