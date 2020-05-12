package com.twocheckout;
import com.google.gson.Gson;
import com.twocheckout.model.Authorization;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public abstract class TwocheckoutCharge {
    public static String checkout_url() {
        return Twocheckout.baseURL;
    }

    public static String form(HashMap<String, String> args) {
        StringBuilder html = new StringBuilder();
        html.append( "<form id=\"2checkout\" action=\"" + checkout_url() + "/checkout/purchase\" method=\"post\">\n" );
        for (Map.Entry<String, String> entry : args.entrySet())
        {
            html.append( "<input type=\"hidden\" name=\"" + entry.getKey() + "\" value=\"" + entry.getValue() + "\"/>\n" );
        }
        html.append( "<input type=\"submit\" value=\"Checkout\" />\n</form>\n" );
        return html.toString();
    }

    public static String submit(HashMap<String, String> args) {
        StringBuilder html = new StringBuilder();
        html.append( "<form id=\"2checkout\" action=\"" + checkout_url() + "/checkout/purchase\" method=\"post\">\n" );
        for (Map.Entry<String, String> entry : args.entrySet())
        {
            html.append( "<input type=\"hidden\" name=\"" + entry.getKey() + "\" value=\"" + entry.getValue() + "\"/>\n" );
        }
        html.append( "</form>\n" );
        html.append( "<script type=\"text/javascript\">document.getElementById('2checkout').submit();</script>" );
        return html.toString();
    }

    public static String url(HashMap<String, String> args) {
        String url = checkout_url() + "/checkout/purchase?";
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : args.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        String paramString = URLEncodedUtils.format(params, "utf-8");
        return url += paramString;
    }

    public static Authorization authorize(HashMap<String, Object> args) throws TwocheckoutException {
        String urlSuffix = "/checkout/api/1/" + args.get("sellerId") + "/rs/authService";
        String response = TwocheckoutApi.auth(urlSuffix, args);
        TwocheckoutResponse responseObj = new Gson().fromJson(response, TwocheckoutResponse.class);
        response = new Gson().toJson(responseObj.getAuthResponse());
        Authorization responseObject = new Gson().fromJson(response, Authorization.class);
        return responseObject;
    }
}
