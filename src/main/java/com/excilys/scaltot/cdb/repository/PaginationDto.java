package com.excilys.scaltot.cdb.repository;

/**
 * @author Caltot Stéphan
 *
 * 3 mars 2017
 */
public class PaginationDto {
    
    private long pageSize = 10;
    private long currentPage = 0;
    private long numberOfElements = 0;
    private long offset = 0;
    /**
    private String filter = "";
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
    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }
    /**
     * @return the numberOfElements
     */
    public long getNumberOfElements() {
        return numberOfElements;
    }
    /**
     * @param numberOfElements : the numberOfElements to set
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
    
}
