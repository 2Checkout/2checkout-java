package com.twocheckout.model;

import java.math.BigDecimal;

public class LineitemOption {
    private long lineitem_id;
    private long lineitem_option_id;
    private String option_name;
    private String option_value;
    private BigDecimal usd_surcharge;
    private BigDecimal vendor_surcharge;
    private BigDecimal customer_surcharge;

    public long getLineItemId() {
        return lineitem_id;
    }
    
    public long getLineitemOptionId() {
        return lineitem_option_id;
    }
    
    public String getOptionName() {
        return option_name;
    }
    
    public String getOptionValue() {
        return option_value;
    }
    
    public BigDecimal getUsdSurcharge() {
        return usd_surcharge;
    }
    
    public BigDecimal getVendorSurcharge() {
        return vendor_surcharge;
    }
    
    public BigDecimal getCustomerSurcharge() {
        return customer_surcharge;
    }
}
