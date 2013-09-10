package com.twocheckout.model;
import java.math.BigDecimal;
import java.util.HashMap;
import com.google.gson.Gson;
import com.twocheckout.TwocheckoutApi;
import com.twocheckout.TwocheckoutResponse;

public class Lineitem {
    private long lineitem_id;
    private Billing billing;
    private long affiliate_vendor_id;
    private BigDecimal commission;
    private long commission_affiliate_vendor_id;
    private String commission_flat_rate;
    private String commission_percentage;
    private String commission_type;
    private BigDecimal commission_usd_amount;
    private BigDecimal customer_amount;
    private String flat_rate;
    private int installment;
    private long invoice_id;
    private long lc_affiliate_vendor_id;
    private BigDecimal lc_usd_amount;
    private long linked_id;
    private LineitemOption[] options;
    private String percentage;
    private String product_description;
    private String product_duration;
    private BigDecimal product_handling;
    private long product_id;
    private Boolean product_is_cart;
    private String product_name;
    private BigDecimal product_price;
    private String product_recurrence;
    private BigDecimal product_startup_fee;
    private String product_tangible;
    private long sale_id;
    private String status;
    private String type;
    private BigDecimal usd_amount;
    private BigDecimal usd_commission;
    private BigDecimal vendor_amount;
    private String vendor_product_id;

    public long getLineitemID() {
        return lineitem_id;
    }
    
    public Billing getBilling() {
        return billing;
    }
    
    public long getAffiliateVendorId() {
        return affiliate_vendor_id;
    }
    
    public BigDecimal getCommission() {
        return commission;
    }
    
    public long getCommissionAffiliateVendorId() {
        return commission_affiliate_vendor_id;
    }
    
    public String getCommissionFlatRate() {
        return commission_flat_rate;
    }
    
    public String getCommissionPercentage() {
        return commission_percentage;
    }
    
    public String getCommissionType() {
        return commission_type;
    }
    
    public BigDecimal getCommissionUsdAmount() {
        return commission_usd_amount;
    }
    
    public BigDecimal getCustomerAmount() {
        return customer_amount;
    }
    
    public String getFlatRate() {
        return flat_rate;
    }
    
    public int getInstallment() {
        return installment;
    }
    
    public long getInvoiceId() {
        return invoice_id;
    }
    
    public long getLcAffiliateVendorId() {
        return lc_affiliate_vendor_id;
    }
    
    public BigDecimal getLcUsdAmount() {
        return lc_usd_amount;
    }
    
    public long getLinkedId() {
        return linked_id;
    }
    
    public LineitemOption[] getOptions() {
        return options;
    }
    
    public String getPercentage() {
        return percentage;
    }
    
    public String getProductDescription() {
        return product_description;
    }
    
    public String getProductDuration() {
        return product_duration;
    }
    
    public BigDecimal getProductHandling() {
        return product_handling;
    }
    
    public long getProductId() {
        return product_id;
    }
    
    public Boolean getProductIsCart() {
        return product_is_cart;
    }
    
    public String getProductName() {
        return product_name;
    }
    
    public BigDecimal getProductPrice() {
        return product_price;
    }
    public String getProductRecurrence() {
        return product_recurrence;
    }
    
    public BigDecimal getProductStartupFees() {
        return product_startup_fee;
    }
    
    public String getProductTangible() {
        return product_tangible;
    }
    
    public long getSaleId() {
        return sale_id;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getType() {
        return type;
    }
    
    public BigDecimal getUsdAmount() {
        return usd_amount;
    }
    
    public BigDecimal getUsdCommission() {
        return usd_commission;
    }
    
    public BigDecimal getVendorAmount() {
        return vendor_amount;
    }
    
    public String getVendorProductId() {
        return vendor_product_id;
    }
    
    public TwocheckoutResponse refund(HashMap<String, String> args) throws Exception {
        args.put("lineitem_id", String.valueOf(lineitem_id));
        String urlSuffix = "sales/refund_lineitem";
        String response = TwocheckoutApi.post(urlSuffix, args);
        TwocheckoutResponse responseObj = new Gson().fromJson(response, TwocheckoutResponse.class);
        return responseObj;
    }
    
    public TwocheckoutResponse stop() throws Exception {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("lineitem_id", String.valueOf(lineitem_id));
        String urlSuffix = "sales/stop_lineitem_recurring";
        String response = TwocheckoutApi.post(urlSuffix, args);
        TwocheckoutResponse responseObj = new Gson().fromJson(response, TwocheckoutResponse.class);
        return responseObj;
    }
}
