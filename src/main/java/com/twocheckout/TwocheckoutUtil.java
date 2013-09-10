package com.twocheckout;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TwocheckoutUtil {

    public static ArrayList<NameValuePair> convert(HashMap<String, String> params) {
        HashMap<String, String> callParams = params;
        ArrayList<NameValuePair> apiParams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : callParams.entrySet()) {
            apiParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return apiParams;
    }

}
