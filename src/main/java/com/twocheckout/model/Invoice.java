package com.twocheckout.model;
import java.math.BigDecimal;
import java.util.HashMap;
import com.google.gson.Gson;
import com.twocheckout.TwocheckoutApi;
import com.twocheckout.TwocheckoutResponse;

public class Invoice {
    private long invoice_id;
    private BigDecimal customer_total;
    private String date_placed;
    private String date_shipped;
    private String date_vendor_paid;
    private BigDecimal fees_2co;
    private Boolean recurring;
    private String referrer;
    private long sale_id;
    private String status;
    private BigDecimal usd_total;
    private long vendor_id;
    private String vendor_order_id;
    private BigDecimal vendor_total;
    private Lineitem[] lineitems;
    private Shipping shipping;

    public Long getInvoiceId() {
        return invoice_id;
    }
    
    public BigDecimal getCustomerTotal() {
        return customer_total;
    }
    
    public String getDatePlaced() {
        return date_placed;
    }
    
    public String getDateShipped() {
        return date_shipped;
    }
    
    public String getDateVendorPaid() {
        return date_vendor_paid;
    }
    
    public Boolean getRecurring() {
        return recurring;
    }
    
    public BigDecimal getFees2co() {
        return fees_2co;
    }
    
    public String getReferrer() {
        return referrer;
    }
    
    public String getStatus() {
        return status;
    }
    
    public BigDecimal getUsdTotal() {
        return usd_total;
    }
    
    public long getSaleId() {
        return sale_id;
    }
    
    public long getVendorId() {
        return vendor_id;
    }
    
    public String getVendorOrderId() {
        return vendor_order_id;
    }
    
    public BigDecimal getVendorTotal() {
        return vendor_total;
    }
    
    public Lineitem[] getLineitems() {
        return lineitems;
    }
    
    public Shipping getShipping() {
        return shipping;
    }
    
    public TwocheckoutResponse refund(HashMap<String, String> args) throws Exception {
        args.put("invoice_id", String.valueOf(invoice_id));
        String urlSuffix = "sales/refund_invoice";
        String response = TwocheckoutApi.post(urlSuffix, args);
        TwocheckoutResponse responseObj = new Gson().fromJson(response, TwocheckoutResponse.class);
        return responseObj;
    }
}
