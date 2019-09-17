package com.twocheckout;

import com.google.gson.Gson;
import com.twocheckout.model.AuthException;
import com.twocheckout.model.AuthExceptions;
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
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.ContentType;
import javax.net.ssl.SSLContext;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class TwocheckoutApi {

    public static String get(String urlSuffix, HashMap<String, String> args) throws TwocheckoutException {
        ArrayList<NameValuePair> params = TwocheckoutUtil.convert(args);
        String apiusername = Twocheckout.apiusername;
        String apipassword = Twocheckout.apipassword;
        String url = "sandbox".equals(Twocheckout.mode)
            ? Twocheckout.sandboxBaseURL + urlSuffix
            : Twocheckout.baseURL + urlSuffix;
        url = addLocationToUrl(url, params);
        String mainObject = null;
        
        try {

            URI uri = new URI(url);
            DefaultHttpClient httpclient = createHttpClient();

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
            checkStatusCodeAdmin(response, responseBody);

            if (responseBody != null) {
                return responseBody;
            }
        } catch (TwocheckoutException e) {
            throw new TwocheckoutException(e.getMessage(), e.getCode());
        } catch (Exception e) {
            throw new TwocheckoutException(e.getMessage(), "500");
        }

        return mainObject;
    }

    public static String post(String urlSuffix, HashMap<String, String> args) throws TwocheckoutException {
        ArrayList<NameValuePair> params = TwocheckoutUtil.convert(args);
        String apiusername = Twocheckout.apiusername;
        String apipassword = Twocheckout.apipassword;
        String url = "sandbox".equals(Twocheckout.mode)
            ? Twocheckout.sandboxBaseURL+urlSuffix
            : Twocheckout.baseURL+urlSuffix;
        String mainObject = null;
           URI uri;
        try {
            uri = new URI(url);
            DefaultHttpClient httpclient = createHttpClient();

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
            checkStatusCodeAdmin(response, responseBody);

            if (responseBody != null) {
                return responseBody;
            }
        } catch (TwocheckoutException e) {
            throw new TwocheckoutException(e.getMessage(), e.getCode());
        } catch (Exception e) {
            throw new TwocheckoutException(e.getMessage(), "500");
        }

        return mainObject;
    }

    public static String auth(String urlSuffix, HashMap<String, Object> args) throws TwocheckoutException {
        args.put("privateKey", Twocheckout.privatekey);
        String request = new Gson().toJson(args);
        String url = "sandbox".equals(Twocheckout.mode)
            ? Twocheckout.sandboxBaseURL+urlSuffix
            : Twocheckout.baseURL+urlSuffix;
        String mainObject = null;
        URI uri;
        try {
            uri = new URI(url);
            DefaultHttpClient httpclient = createHttpClient();

            HttpPost httppost = new HttpPost(uri);
            httppost.setHeader("Accept", "application/json");
            httppost.setHeader("User-Agent", String.format("2Checkout/Java/%s", Twocheckout.VERSION));
            httppost.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2109);
            httppost.setEntity(new StringEntity(request, ContentType.create("application/json")));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);
            httpclient.getConnectionManager().shutdown();
            checkStatusCodeAuth(response, responseBody);

            if (responseBody != null) {
                return responseBody;
            }
        } catch (TwocheckoutException e) {
            throw new TwocheckoutException(e.getMessage(), e.getCode());
        } catch (Exception e) {
            throw new TwocheckoutException(e.getMessage());
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

    private static void checkStatusCodeAdmin(HttpResponse response, String responseBody) throws TwocheckoutException {
        StatusLine status = response.getStatusLine();
        if (status.getStatusCode() != HttpStatus.SC_OK) {
            Errors errors = new Gson().fromJson(responseBody, Errors.class);
            Error[] error = errors.getErrors();
            throw new TwocheckoutException(error[0].getMessage(), error[0].getCode());
        }
    }

    private static void checkStatusCodeAuth(HttpResponse response, String responseBody) throws TwocheckoutException {
        StatusLine status = response.getStatusLine();
        if (status.getStatusCode() != HttpStatus.SC_OK && status.getStatusCode() != HttpStatus.SC_ACCEPTED ) {
            AuthExceptions exceptions = new Gson().fromJson(responseBody, AuthExceptions.class);
            AuthException exception = exceptions.getAuthExceptions();
            throw new TwocheckoutException(exception.getMessage(), exception.getCode());
        }
    }

    private static DefaultHttpClient createHttpClient() throws KeyManagementException, NoSuchAlgorithmException {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        // Get instance of TLSv1.2 SSLContext and init with defaults
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, null, null);
        SSLSocketFactory sf = new SSLSocketFactory(sslContext);

        httpclient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, sf));

        return httpclient;
    }

}
