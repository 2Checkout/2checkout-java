package com.twocheckout;
import com.google.gson.Gson;
import com.twocheckout.model.Company;
import java.util.HashMap;


public class TwocheckoutCompany extends TwocheckoutApi {

    public Company vendor_company_info;

    public static Company retrieve() throws Exception {
        HashMap<String, String> args = new HashMap<String, String>();
        String urlSuffix = "acct/detail_company_info";
        String response = TwocheckoutApi.get(urlSuffix, args);
        TwocheckoutCompany resultObj = new Gson().fromJson(response, TwocheckoutCompany.class);
        response = new Gson().toJson(resultObj.vendor_company_info);
        Company responseObj = new Gson().fromJson(response, Company.class);
        return responseObj;
    }
}
