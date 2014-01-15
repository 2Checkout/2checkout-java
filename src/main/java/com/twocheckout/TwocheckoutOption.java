package com.twocheckout;

import java.util.HashMap;
import com.google.gson.Gson;
import com.twocheckout.model.ProductOption;
import com.twocheckout.model.ProductOptionList;


public class TwocheckoutOption extends TwocheckoutApi {
    public ProductOption[] option;

    public static ProductOption retrieve(String option_id) throws Exception {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("option_id", option_id);
        String urlSuffix = "/api/products/detail_option";
        String response = TwocheckoutApi.get(urlSuffix, args);
        TwocheckoutOption responseObj = new Gson().fromJson(response, TwocheckoutOption.class);
        return responseObj.option[0];
    }

    public static ProductOptionList list(HashMap<String, String> args) throws Exception {
        String urlSuffix = "/api/products/list_options";
        String response = TwocheckoutApi.get(urlSuffix, args);
        ProductOptionList responseObj = new Gson().fromJson(response, ProductOptionList.class);
        return responseObj;
    }

    public static TwocheckoutResponse create(HashMap<String, String> args) throws Exception {
        String urlSuffix = "/api/products/create_option";
        String response = TwocheckoutApi.post(urlSuffix, args);
        TwocheckoutResponse responseObj = new Gson().fromJson(response, TwocheckoutResponse.class);
        return responseObj;
    }

}
