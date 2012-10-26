package com.twocheckout.model;

public class SaleList {
	private PageInfo page_info;
	private Sales[] sale_summary;
	
    public PageInfo getPageInfo() {
        return page_info;
    }
	
    public Sales[] getSales() {
        return sale_summary;
    }
}
