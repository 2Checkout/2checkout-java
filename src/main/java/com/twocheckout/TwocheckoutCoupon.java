package com.twocheckout;
import java.util.HashMap;
import com.google.gson.Gson;
import com.twocheckout.model.Coupon;
import com.twocheckout.model.CouponList;


public class TwocheckoutCoupon extends TwocheckoutApi {
    public Coupon coupon;

    public static Coupon retrieve(String coupon_code) throws TwocheckoutException {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("coupon_code", coupon_code);
        String urlSuffix = "/api/products/detail_coupon";
        String response = TwocheckoutApi.get(urlSuffix, args);
        TwocheckoutCoupon responseObj = new Gson().fromJson(response, TwocheckoutCoupon.class);
        return responseObj.coupon;
    }

    public static CouponList list(HashMap<String, String> args) throws TwocheckoutException {
        String urlSuffix = "/api/products/list_coupons";
        String response = TwocheckoutApi.get(urlSuffix, args);
        CouponList responseObj = new Gson().fromJson(response, CouponList.class);
        return responseObj;
    }

    public static TwocheckoutResponse create(HashMap<String, String> args) throws TwocheckoutException {
        String urlSuffix = "/api/products/create_coupon";
        String response = TwocheckoutApi.post(urlSuffix, args);
        TwocheckoutResponse responseObj = new Gson().fromJson(response, TwocheckoutResponse.class);
        return responseObj;
    }
}
