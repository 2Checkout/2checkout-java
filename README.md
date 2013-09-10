2Checkout Java Library
=====================

This library provides developers with a simple set of bindings to the 2Checkout purchase routine, Instant Notification Service and Back Office API.

Full documentation for each binding will be provided in the [Wiki](https://github.com/2checkout/2checkout-java/wiki).

Installation
------------

Add the included 2checkout-java.jar and it's dependencies listed below.
* [HttpClient 4.2.1](http://hc.apache.org/downloads.cgi)
* [gson](http://code.google.com/p/google-gson/)

You can also build the JAR yourself using Gradle after downloading and adding the dependencies under the 'lib' directory.

Example API Usage
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
