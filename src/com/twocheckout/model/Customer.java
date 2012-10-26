package com.twocheckout.model;

public class Customer {
	private String address_1;
	private String address_2;
	private long address_id;
	private String cardholder_name;
	private String city;
	private String country_code;
	private String country_name;
	private String customer_id;
	private String email_address;
	private String first_name;
	private String lang;
	private String last_name;
	private String middle_initial;
	private PayMethod pay_method;
	
    public String getAddress1() {
        return address_1;
    }
    
    public String getAddress2() {
        return address_2;
    }
    
    public long getAddressId() {
        return address_id;
    }
    
    public String getCardholderName() {
        return cardholder_name;
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
    
    public String getCustomerId() {
        return customer_id;
    }
    public String getEmailAddress() {
        return email_address;
    }
    
    public String getFirstName() {
        return first_name;
    }
    
    public String getLang() {
        return lang;
    }
    
    public String getLastName() {
        return last_name;
    }
    
    public String getMiddleInitial() {
        return middle_initial;
    }
    
    public PayMethod getPayMethod() {
        return pay_method;
    }
}
