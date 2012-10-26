package com.twocheckout.model;

public class Shipping {
	private String address_1;
	private String address_2;
	private String city;
	private String country_code;
	private String country_name;
	private String date_shipped;
	private long invoice_id;
	private String postal_code;
	private long shipping_address_id;
	private long shipping_id;
	private long shipping_method_id;
	private String shipping_method_name;
	private String shipping_name;
	private String state;
	private String tracking_number;
	private String tracking_url;
	
    public String getAddress1() {
        return address_1;
    }
    
    public String getAddress2() {
        return address_2;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getCountryCode() {
        return country_code;
    }
    
    public String getCountryName() {
        return country_name;
    }
    
    public String getDateShipped() {
        return date_shipped;
    }
    
    public long getInvoiceId() {
        return invoice_id;
    }
    
    public String getPostalCode() {
        return postal_code;
    }
    
    public long getShippingAddressId() {
        return shipping_address_id;
    }
    
    public long getShippingId() {
        return shipping_id;
    }
    
    public long getShippingMethodId() {
        return shipping_method_id;
    }
    
    public String getShippingMethodName() {
        return shipping_method_name;
    }
    
    public String getShippingName() {
        return shipping_name;
    }
    
    public String getState() {
        return state;
    }
    
    public String getTrackingNumber() {
        return tracking_number;
    }
    
    public String getTrackingUrl() {
        return tracking_url;
    }
}
