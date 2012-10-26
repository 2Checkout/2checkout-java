package com.twocheckout.model;

public class PageInfo {
	private int cur_page;
	private int first_entry;
	private int first_page;
	private String first_page_url;
	private int last_entry;
	private int last_page;
	private String last_page_url;
	private int next_page;
	private int pagesize;
	private int previous_page;
	private int total_entries;
	
    public int getCurPage() {
        return cur_page;
    }
    
    public int getFirstEntry() {
        return first_entry;
    }
    
    public int getFirstPage() {
        return first_page;
    }
    
    public String getFirstPageUrl() {
        return first_page_url;
    }
    
    public int getLastEntry() {
        return last_entry;
    }
    
    public int getLastPage() {
        return last_page;
    }
    
    public String getLastPageUrl() {
        return last_page_url;
    }
    
    public int getNextPage() {
        return next_page;
    }
    
    public int getPageSize() {
        return pagesize;
    }
    
    public int getPreviousPage() {
        return previous_page;
    }
    
    public int getTotalEntries() {
        return total_entries;
    }
}
