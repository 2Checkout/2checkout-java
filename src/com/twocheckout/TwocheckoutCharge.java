package com.twocheckout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public abstract class TwocheckoutCharge {
	
	public static String form(HashMap<String, String> args) {
		StringBuilder html = new StringBuilder();
		html.append( "<form id=\"2checkout\" action=\"https://www.2checkout.com/checkout/spurchase\" method=\"post\">\n" );
		for (Map.Entry<String, String> entry : args.entrySet())
		{
			html.append( "<input type=\"hidden\" name=\"" + entry.getKey() + "\" value=\"" + entry.getValue() + "\"/>\n" );
		}
        html.append( "<input type=\"submit\" value=\"Checkout\" />\n</form>\n" );
        return html.toString();
	}
	
	public static String submit(HashMap<String, String> args) {
		StringBuilder html = new StringBuilder();
		html.append( "<form id=\"2checkout\" action=\"https://www.2checkout.com/checkout/spurchase\" method=\"post\">\n" );
		for (Map.Entry<String, String> entry : args.entrySet())
		{
			html.append( "<input type=\"hidden\" name=\"" + entry.getKey() + "\" value=\"" + entry.getValue() + "\"/>\n" );
		}
		html.append( "</form>\n" );
        html.append( "<script type=\"text/javascript\">document.getElementById('2checkout').submit();</script>" );
        return html.toString();
	}
	
	public static String url(HashMap<String, String> args) {
		String url = "https://www.2checkout.com/checkout/spurchase?";
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : args.entrySet()) {
		    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		return url += paramString;
	}
}
