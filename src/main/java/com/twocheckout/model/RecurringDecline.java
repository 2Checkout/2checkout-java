package com.twocheckout.model;

public class RecurringDecline {
    private String date_declined;
    private String date_updated;
    private String decline_code;
    private String pay_method;
    private String pplus_code;
    private String retries;
    private String sale_id;

    public String getDateDeclined() {
        return date_declined;
    }

    public String getDateUpdated() {
        return date_updated;
    }

    public String getDeclineCode() {
        return decline_code;
    }

    public String getPayMethod() {
        return pay_method;
    }

    public String getPplusCode() {
        return pplus_code;
    }

    public String getRetries() {
        return retries;
    }

    public String getSaleId() {
        return sale_id;
    }
}
