package com.twocheckout;
import java.util.HashMap;
import com.google.gson.Gson;
import com.twocheckout.model.Product;
import com.twocheckout.model.ProductList;


public class TwocheckoutProduct extends TwocheckoutApi {
    public Product product;

    public static Product retrieve(String product_id) throws TwocheckoutException {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("product_id", product_id);
        String urlSuffix = "/api/products/detail_product";
        String response = TwocheckoutApi.get(urlSuffix, args);
        TwocheckoutProduct resultObj = new Gson().fromJson(response, TwocheckoutProduct.class);
        response = new Gson().toJson(resultObj.product);
        Product responseObj = new Gson().fromJson(response, Product.class);
        return responseObj;
    }

    public static ProductList list(HashMap<String, String> args) throws TwocheckoutException {
        String urlSuffix = "/api/products/list_products";
        String response = TwocheckoutApi.get(urlSuffix, args);
        ProductList responseObj = new Gson().fromJson(response, ProductList.class);
        return responseObj;
    }

    public static TwocheckoutResponse create(HashMap<String, String> args) throws TwocheckoutException {
        String urlSuffix = "/api/products/create_product";
        String response = TwocheckoutApi.post(urlSuffix, args);
        TwocheckoutResponse responseObj = new Gson().fromJson(response, TwocheckoutResponse.class);
        return responseObj;
    }
}
