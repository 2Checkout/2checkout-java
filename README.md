DEPRECATED!!! This SDK can only be used with the legacy 2Checkout platform which is depreciated. Please use [2checkout-java-sdk](https://github.com/2Checkout/2checkout-java-sdk) with the current 2Checkout platform.
=====================

This library provides developers with a simple set of bindings to the 2Checkout purchase routine, Instant Notification Service and Back Office API.

Full documentation for each binding will be provided in the [Wiki](https://github.com/2checkout/2checkout-java/wiki).

Installation
------------

Add the included 2checkout-java.jar and it's dependencies listed below.
* [HttpClient 4.2.1](http://hc.apache.org/downloads.cgi)
* [gson](http://code.google.com/p/google-gson/)

You can also build the JAR yourself using Gradle after downloading and adding the dependencies under the 'lib' directory.


Example Purchase API Usage
-----------------

*Example Usage:*

```java
Twocheckout.privatekey = "3508079E-5383-44D4-BF69-DC619C0D9811";

try {
    HashMap<String, String> billing = new HashMap<String, String>();
    billing.put("name", "Testing Tester");
    billing.put("addrLine1", "xvxcvxcvxcvcx");
    billing.put("city", "Columbus");
    billing.put("state", "Ohio");
    billing.put("country", "USA");
    billing.put("zipCode", "43230");
    billing.put("email", "tester@2co.com");
    billing.put("phone", "555-555-5555");

    HashMap<String, Object> request = new HashMap<String, Object>();
    request.put("sellerId", "1817037");
    request.put("merchantOrderId", "test123");
    request.put("token", "MGI4OTU0OTQtMDIxNi00YThlLTliOTctZjg1YmJiMzg0MjA3");
    request.put("currency", "USD");
    request.put("total", "1.00");
    request.put("billingAddr", billing);

    Authorization response = TwocheckoutCharge.authorize(request);
    String message = response.getResponseMsg();
} catch (Exception e) {
    String message = e.toString();
}
```

*Example Response: (toJson)*

```json
{
    "type": "AuthResponse",
    "responseCode": "APPROVED",
    "currencyCode": "USD",
    "orderNumber": "205181062452",
    "transactionId": "205181062461",
    "responseMsg": "Successfully authorized the provided credit card",
    "merchantOrderId": "test123",
    "total": 1,
    "lineItems": [
        {
            "type": "product",
            "name": "test123",
            "description": "",
            "price": 1,
            "quantity": 1,
            "tangible": "N",
            "productId": "",
            "options": []
        }
    ]
}
```


Example Admin API Usage
-----------------

*Example Usage:*

```java
Twocheckout.apiusername = "APIuser1817037";
Twocheckout.apipassword = "APIpass1817037";

HashMap<String, String> params = new HashMap<String, String>();
params.put("comment", "test");
params.put("category", "1");

Sale sale = TwocheckoutSale.retrieve("4774380224");
TwocheckoutResponse result = sale.refund(params);
String message = result.getResponseMessage();
```

*Example Response: (toJson)*

```json
{
   "response_code" : "OK",
   "response_message" : "refund added to invoice"
}
```

Example Checkout Usage:
-----------------------

*Example Usage:* _(Using Spark)_

```java
import spark.*;
import static spark.Spark.*;

  get(new Route("/") {
     @Override
     public Object handle(Request request, Response response) {

         HashMap<String, String> params = new HashMap<String, String>();
         params.put("sid", "1817037");
         params.put("mode", "2CO");
         params.put("li_0_type", "product");
         params.put("li_0_name", "Example Product");
         params.put("li_0_price", "1.00");

         String form = TwocheckoutCharge.submit(params);

         form = "<html>" + form + "</html>";

         return form;
    }
  });
```

Example Return Usage:
---------------------

*Example Usage:* _(Using Spark)_

```java
  post(new Route("/") {
     @Override
     public Object handle(Request request, Response response) {
         HashMap<String, String> params = new HashMap<String, String>();
         params.put("sid", request.queryParams("sid"));
         params.put("total", request.queryParams("total"));
         params.put("order_number", request.queryParams("order_number"));
         params.put("key", request.queryParams("key"));
         Boolean result = TwocheckoutReturn.check(params, "tango");
         if (result == true) {
             return "Success";
         } else {
             return "Fail";
         }
     }
  });
```

Example INS Usage:
------------------

*Example Usage:* _(Using Spark)_

```java
  post(new Route("/") {
     @Override
     public Object handle(Request request, Response response) {
         HashMap<String, String> params = new HashMap<String, String>();
         params.put("vendor_id", request.queryParams("vendor_id"));
         params.put("invoice_id", request.queryParams("invoice_id"));
         params.put("sale_id", request.queryParams("sale_id"));
         params.put("md5_hash", request.queryParams("md5_hash"));
         Boolean result = TwocheckoutNotification.check(params, "tango");
         if (result == true) {
             return "Success";
         } else {
             return "Fail";
         }
     }
  });
```

Exceptions:
-----------
TwocheckoutException exceptions are thrown by if an error has returned. It is best to catch these exceptions so that they can be gracefully handled in your application.

*Example Usage*

```java
HashMap<String, String> params = new HashMap<String, String>();
params.put("comment", "test");
params.put("category", "1");
try {
    Sale sale = TwocheckoutInvoice.retrieve("4832573658");
    TwocheckoutResponse result = sale.refund(params);
} catch (TwocheckoutException e) {
    String message = e.toString();
}
```

Full documentation for each binding will be provided in the [Wiki](https://github.com/2checkout/2checkout-java/wiki).
