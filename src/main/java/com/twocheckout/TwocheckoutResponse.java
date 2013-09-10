package com.twocheckout;

public class TwocheckoutResponse {
    private String response_code;
    private String response_message;
    private String product_id;
    private String assigned_product_id;
    private String coupon_code;
    private String option_id;

    public String getResponseCode() {
        return response_code;
    }
    public void setResponseCode(String response_code) {
        this.response_code = response_code;
    }
    public String getResponseMessage() {
        return response_message;
    }
    public void setResponseMessage(String response_message) {
        this.response_message = response_message;
    }
    public String getProductId() {
        return product_id;
    }
    public void setProductId(String product_id) {
        this.product_id = product_id;
    }
    public String getAssignedProductId() {
        return assigned_product_id;
    }
    public void setAssignedProductId(String assigned_product_id) {
        this.assigned_product_id = assigned_product_id;
    }
    public String getCouponCode() {
        return coupon_code;
    }
    public void setCouponCode(String coupon_code) {
        this.coupon_code = coupon_code;
    }
    public String getOptionId() {
        return option_id;
    }
    public void setOptionId(String option_id) {
        this.option_id = option_id;
    }
}
