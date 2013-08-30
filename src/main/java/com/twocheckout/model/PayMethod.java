package com.twocheckout.model;

public class PayMethod {
    private String avs;
    private String cvv;
    private int first_six_digits;
    private int last_two_digits;
    private String method;

    public String getAvs() {
        return avs;
    }
    
    public String getCvv() {
        return cvv;
    }
    
    public int getFirstSixDigits() {
        return first_six_digits;
    }
    
    public int getLastTwoDigits() {
        return last_two_digits;
    }
    
    public String getMethod() {
        return method;
    }
}
