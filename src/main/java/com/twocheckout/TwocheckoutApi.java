package com.twocheckout;

import com.google.gson.Gson;
import com.twocheckout.model.Error;
import com.twocheckout.model.Errors;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

 
public abstract class TwocheckoutApi {
 
    public static String get(String urlSuffix, HashMap<String, String> args) throws Exception {
        ArrayList<NameValuePair> params = TwocheckoutUtil.convert(args);
        String apiusername = Twocheckout.apiusername;
        String apipassword = Twocheckout.apipassword;
        String url = Twocheckout.baseURL+urlSuffix;
        url = addLocationToUrl(url, params);
        String mainObject = null;
        
        try {

            URI uri = new URI(url);
            DefaultHttpClient httpclient = new DefaultHttpClient();
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                        new UsernamePasswordCredentials(apiusername, apipassword));

            HttpGet httpget = new HttpGet(uri);
            httpget.setHeader("Accept", "application/json");
            httpget.setHeader("User-Agent", String.format("2Checkout/Java/%s", Twocheckout.VERSION));
            httpget.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2109);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);
            httpclient.getConnectionManager().shutdown();
            checkStatusCode(response, responseBody);

            if (responseBody != null) {
                return responseBody;
            }

        } catch (Exception e) {
            throw e;
        }

        return mainObject;
    }

    public static String post(String urlSuffix, HashMap<String, String> args) throws Exception {
        ArrayList<NameValuePair> params = TwocheckoutUtil.convert(args);
        String apiusername = Twocheckout.apiusername;
        String apipassword = Twocheckout.apipassword;
        String url = Twocheckout.baseURL+urlSuffix;
        String mainObject = null;
           URI uri;
        try {
            uri = new URI(url);
            DefaultHttpClient httpclient = new DefaultHttpClient();
            httpclient.getCredentialsProvider().setCredentials(
               new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                  new UsernamePasswordCredentials(apiusername, apipassword));

            HttpPost httppost = new HttpPost(uri);
            httppost.setHeader("Accept", "application/json");
            httppost.setHeader("User-Agent", String.format("2Checkout/Java/%s", Twocheckout.VERSION));
            httppost.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2109);
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);
            httpclient.getConnectionManager().shutdown();
            checkStatusCode(response, responseBody);

            if (responseBody != null) {
                return responseBody;
            }

        } catch (Exception e) {
            throw e;
        }

        return mainObject;
    }

    public static String addLocationToUrl(String url, List<NameValuePair> params){
        if(!url.endsWith("?"))
            url += "?";

        String paramString = URLEncodedUtils.format(params, "utf-8");
        url += paramString;
        return url;
    }

    private static void checkStatusCode(HttpResponse response, String responseBody) throws TwocheckoutException {
        StatusLine status = response.getStatusLine();
        if (status.getStatusCode() != HttpStatus.SC_OK) {
            Errors errors = new Gson().fromJson(responseBody, Errors.class);
            Error[] error = errors.getErrors();
            throw new TwocheckoutException(error[0].getMessage());
        }
    }

}
