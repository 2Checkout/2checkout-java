package com.twocheckout.model;

import java.math.BigDecimal;
import java.util.HashMap;
import com.google.gson.Gson;
import com.twocheckout.TwocheckoutApi;
import com.twocheckout.TwocheckoutCoupon;
import com.twocheckout.TwocheckoutResponse;
import com.twocheckout.TwocheckoutException;


public class Coupon {
    private String coupon_code;
    private String date_expire;
    private BigDecimal minimum_purchase;
    private int percentage_off;
    private String type;
    private BigDecimal value_off;

    public String getCouponCode() {
        return coupon_code;
    }
    public void setCouponCode(String coupon_code) {
        this.coupon_code = coupon_code;
    }
    public String getDateExpire() {
        return date_expire;
    }
    public void setDateExpire(String date_expire) {
        this.date_expire = date_expire;
    }
    public BigDecimal getMinimumPurchase() {
        return minimum_purchase;
    }
    public void setMinimumPurchase(BigDecimal minimum_purchase) {
        this.minimum_purchase = minimum_purchase;
    }
    public int getPercentageOff() {
        return percentage_off;
    }
    public void setPercentageOff(int percentage_off) {
        this.percentage_off = percentage_off;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public BigDecimal getValueOff() {
        return value_off;
    }
    public void setValueOff(BigDecimal value_off) {
        this.value_off = value_off;
    }

    public TwocheckoutResponse update() throws TwocheckoutException {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("coupon_code", coupon_code);
        args.put("date_expire", date_expire);
        args.put("type", type);
        args.put("minimum_purchase", String.valueOf(minimum_purchase));
        if (percentage_off != 0) {
            args.put("percentage_off", String.valueOf(percentage_off));
        }
        if (value_off != null) {
            args.put("value_off", String.valueOf(value_off));
        }
        String urlSuffix = "/api/products/update_coupon";
        String response = TwocheckoutApi.post(urlSuffix, args);
        TwocheckoutResponse responseObj = new Gson().fromJson(response, TwocheckoutResponse.class);
        return responseObj;
    }

    public TwocheckoutResponse delete() throws TwocheckoutException {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("coupon_code", String.valueOf(coupon_code));
        String urlSuffix = "/api/products/delete_coupon";
        String response = TwocheckoutApi.post(urlSuffix, args);
        TwocheckoutResponse responseObj = new Gson().fromJson(response, TwocheckoutResponse.class);
        return responseObj;
    }
    
    public Coupon refresh() throws TwocheckoutException {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("coupon_code", coupon_code);
        String urlSuffix = "/api/products/detail_coupon";
        String response = TwocheckoutApi.get(urlSuffix, args);
        TwocheckoutCoupon responseObj = new Gson().fromJson(response, TwocheckoutCoupon.class);
        return responseObj.coupon;
    }
}
