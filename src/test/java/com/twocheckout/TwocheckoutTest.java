package com.twocheckout;

import com.google.common.collect.Maps;
import com.twocheckout.model.*;
import org.junit.BeforeClass;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import java.util.HashMap;

public class TwocheckoutTest {

    @BeforeClass public static void setUp() {
        Twocheckout.apiusername = "test_api_250111206876";
        Twocheckout.apipassword = "Qwerty123";
        Twocheckout.privatekey = "1D336D05-05AA-40A4-AEF6-DF58D222E6F4";
    }

    static String product_id;

    @Test public void testSaleRetrieve() throws Exception {
        try {
            Sale sale = TwocheckoutSale.retrieve("250334615672");
            String sale_id = String.valueOf(sale.getSaleId());
            assertEquals("250334615672", sale_id);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: Unable to find record.", message);
        }
    }

    @Test public void testInvoiceRetrieve() throws Exception {
        try {
            Sale sale = TwocheckoutInvoice.retrieve("250334725631");
            String sale_id = String.valueOf(sale.getSaleId());
            assertEquals("250334725632", sale_id);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: Unable to find record.", message);
        }
    }

    @Test public void testInvoiceRefund() throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("comment", "test");
        params.put("category", "1");

        try {
            Sale sale = TwocheckoutInvoice.retrieve("250334725631");
            TwocheckoutResponse result = sale.refund(params);
            String message = result.getResponseMessage();
            assertEquals("refund added to invoice", message);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: Amount greater than remaining balance on invoice.", message);
        }
    }

    @Test public void testSaleRefund() throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("comment", "test");
        params.put("category", "1");
        try {
            Sale sale = TwocheckoutSale.retrieve("250334226612");
            TwocheckoutResponse result = sale.refund(params);
            String message = result.getResponseMessage();
            assertEquals("refund added to invoice", message);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: Amount greater than remaining balance on invoice.", message);
        }
    }

    @Test public void testSaleStop() throws Exception {
        Sale sale = TwocheckoutSale.retrieve("250334624367");
        TwocheckoutResponse result = sale.stop();
        String message = result.getResponseMessage();
        assertEquals("No active recurring lineitems.", message);
    }


    @Test public void testLineitemStop() throws Exception {
        try {
            Sale sale = TwocheckoutSale.retrieve("250192108517");
            Invoice[] invoices = sale.getInvoices();
            Invoice invoice = invoices[invoices.length-1];
            Lineitem[] lineitems = invoice.getLineitems();
            Lineitem lineitem = lineitems[0];
            TwocheckoutResponse result = lineitem.stop();
            String message = result.getResponseMessage();
            assertEquals("Recurring billing stopped for lineitem", message);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: Lineitem is not scheduled to recur.", message);
        }
    }

    @Test public void testLineitemRefund() throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("comment", "test");
        params.put("category", "1");
        try {
            Sale sale = TwocheckoutSale.retrieve("250334226612");
            Invoice[] invoices = sale.getInvoices();
            Invoice invoice = invoices[invoices.length-1];
            Lineitem[] lineitems = invoice.getLineitems();
            Lineitem lineitem = lineitems[0];
            TwocheckoutResponse result = lineitem.refund(params);
            String message = result.getResponseMessage();
            assertEquals("lineitem refunded", message);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: Lineitem amount greater than remaining balance on invoice.", message);
        }
    }

    @Test public void testSaleComment() throws Exception {
        Sale sale = TwocheckoutSale.retrieve("250334624367");
        HashMap<String, String> params = new HashMap<>();
        params.put("sale_comment", "test");
        TwocheckoutResponse result = sale.comment(params);
        String message = result.getResponseMessage();
        assertEquals("Created comment successfully.", message);
    }

    @Test public void testSaleShip() throws Exception {
        try {
            Sale sale = TwocheckoutSale.retrieve("250334226612");
            HashMap<String, String> params = new HashMap<>();
            params.put("tracking_number", "test");
            TwocheckoutResponse result = sale.ship(params);
            String message = result.getResponseMessage();
            assertEquals("Sale marked shipped.", message);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: Sale already marked shipped.", message);
        }
    }

    @Test public void testProductCreate() throws Exception {
        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("name", "test");
            params.put("price", "1.00");
            params.put("vendor_product_id", "Test");
            params.put("description", "Test Short Description");
            params.put("long_description", "Test Long Description");
            params.put("approved_url", "http://www.2checkout.com");
            params.put("tangible", "1");
            params.put("weight", "1.50");
            params.put("handling", "1.00");
            params.put("recurring", "1");
            params.put("recurrence", "1 Month");
            params.put("duration", "Forever");
            TwocheckoutResponse result = TwocheckoutProduct.create(params);
            String message = result.getResponseMessage();
            assertEquals("Product successfully created.", message);
        } catch (TwocheckoutException e) {
            e.printStackTrace();
        }
    }

    @Test public void testReturnFail() {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("sid", "1817037");
        params.put("total", "5.00");
        params.put("order_number", "4819138002");
        params.put("key", "37497D9861B21618CDB9AEE6AC96E30A");
        Boolean result = TwocheckoutReturn.check(params, "tango");
        assertFalse(result);
    }

    @Test public void testReturnSuccess() {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("sid", "1817037");
        params.put("total", "1.00");
        params.put("order_number", "4819138002");
        params.put("key", "37497D9861B21618CDB9AEE6AC96E30A");
        Boolean result = TwocheckoutReturn.check(params, "tango");
        assertTrue(result);
    }

    @Test public void testNotificationFail() {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("vendor_id", "1817037");
        params.put("invoice_id", "4343434343");
        params.put("sale_id", "4819138002");
        params.put("md5_hash", "A152B3D993B4F97E2E2A41552A4769A7");
        Boolean result = TwocheckoutNotification.check(params, "tango");
        assertFalse(result);
    }

    @Test public void testNotificationSuccess() {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("vendor_id", "1817037");
        params.put("invoice_id", "4343434343");
        params.put("sale_id", "4819138002");
        params.put("md5_hash", "A152B3D993B4F97E2E2A41552A4769A8");
        Boolean result = TwocheckoutNotification.check(params, "tango");
        assertTrue(result);
    }

    @Test public void testChargeAuth() throws Exception {
        try {
            HashMap<String, String> billing = new HashMap<>();
            billing.put("name", "John Doe");
            billing.put("addrLine1", "123 Test St");
            billing.put("city", "Columbus");
            billing.put("state", "Ohio");
            billing.put("country", "USA");
            billing.put("zipCode", "43230");
            billing.put("email", "tester@2co.com");
            billing.put("phone", "555-555-5555");

            HashMap<String, Object> request = new HashMap<>();
            request.put("sellerId", "250111206876");
            request.put("merchantOrderId", "test123");
            request.put("token", "OWVhOTZiYjEtY2ZmMi00NDhhLThjNmUtM2Y4ZmVkYTNhMDE5");
            request.put("currency", "USD");
            request.put("total", "1.00");
            request.put("billingAddr", billing);
            request.put("demo", true);

            Authorization response = TwocheckoutCharge.authorize(request);
            assertEquals("APPROVED", response.getResponseCode());
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: " +
                    "Unauthorized", message);
        }
    }

}
