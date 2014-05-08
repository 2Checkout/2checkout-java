package com.twocheckout;
import java.util.HashMap;
import com.google.gson.Gson;
import com.twocheckout.model.Contact;


public class TwocheckoutContact extends TwocheckoutApi {

    public Contact vendor_contact_info;

    public static Contact retrieve() throws TwocheckoutException {
        HashMap<String, String> args = new HashMap<String, String>();
        String urlSuffix = "/api/acct/detail_contact_info";
        String response = TwocheckoutApi.get(urlSuffix, args);
        TwocheckoutContact resultObj = new Gson().fromJson(response, TwocheckoutContact.class);
        response = new Gson().toJson(resultObj.vendor_contact_info);
        Contact responseObj = new Gson().fromJson(response, Contact.class);
        return responseObj;
    }
}
