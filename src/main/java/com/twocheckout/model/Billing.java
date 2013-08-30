package com.twocheckout.model;
import java.math.BigDecimal;

public class Billing {
    private BigDecimal amount;
    private String bill_method;
    private Long billing_id;
    private BigDecimal customer_amount;
    private Long customer_id;
    private String date_deposited;
    private String date_end;
    private String date_fail;
    private String date_next;
    private String date_pending;
    private String date_start;
    private Long lineitem_id;
    private String recurring_status;
    private String status;
    private BigDecimal usd_amount;
    private BigDecimal vendor_amount;

    public BigDecimal getAmount() {
        return amount;
    }
    
    public String getBillMethod() {
        return bill_method;
    }
    
    public Long getBillingId() {
        return billing_id;
    }
    
    public BigDecimal getCustomerAmount() {
        return customer_amount;
    }
    
    public Long getCustomerId() {
        return customer_id;
    }
    
    public String getDateDeposited() {
        return date_deposited;
    }
    
    public String getDateEnd() {
        return date_end;
    }
    
    public String getDateFail() {
        return date_fail;
    }
    
    public String getDateNext() {
        return date_next;
    }
    
    public String getDatePending() {
        return date_pending;
    }
    
    public String getDateStart() {
        return date_start;
    }
    
    public Long getLineitemId() {
        return lineitem_id;
    }
    
    public String getRecurringStatus() {
        return recurring_status;
    }
    public String getStatus() {
        return status;
    }
    public BigDecimal getUsdAmount() {
        return usd_amount;
    }
    public BigDecimal getVendorAmount() {
        return vendor_amount;
    }
}
