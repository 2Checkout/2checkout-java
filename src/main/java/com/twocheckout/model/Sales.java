package com.twocheckout.model;
import java.math.BigDecimal;

public class Sales {
    private String sale_id;
    private String date_placed;
    private String customer_name;
    private String recurring;
    private String recurring_declined;
    private BigDecimal usd_total;
    private String decline_code;
    private String sale_url;

    public String getSaleId() {
        return sale_id;
    }
    
    public String getDatePlaced() {
        return date_placed;
    }

    public String getCustomerName() {
        return customer_name;
    }

    public Boolean getRecurring() {
        return recurring == "1" ? true : false;
    }

    public String getRecurringDeclined() {
        return recurring_declined;
    }

    public BigDecimal getUsdTotal() {
        return usd_total;
    }

    public String getDeclineCode() {
        return decline_code;
    }

    public String getSaleUrl() {
        return sale_url;
    }
    
}
