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
        Twocheckout.apiusername = "testlibraryapi901248204";
        Twocheckout.apipassword = "testlibraryapi901248204PASS";
        Twocheckout.privatekey = "BE632CB0-BB29-11E3-AFB6-D99C28100996";
        Twocheckout.mode = "sandbox";
    }

    static String product_id;
    static String option_id;
    static String coupon_code;

    @Test public void testSaleRetrieve() throws Exception {
        try {
            Sale sale = TwocheckoutSale.retrieve("9093717691800");
            String sale_id = String.valueOf(sale.getSaleId());
            assertEquals("9093717691800", sale_id);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: Unable to find record.", message);
        }
    }

    @Test public void testInvoiceRetrieve() throws Exception {
        try {
            Sale sale = TwocheckoutInvoice.retrieve("9093717691821");
            String sale_id = String.valueOf(sale.getSaleId());
            assertEquals("9093717691800", sale_id);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: Unable to find record.", message);
        }
    }

    @Test public void testInvoiceRefund() throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("comment", "test");
        params.put("category", "1");
        try {
            Sale sale = TwocheckoutInvoice.retrieve("9093717691821");
            TwocheckoutResponse result = sale.refund(params);
            String message = result.getResponseMessage();
            assertEquals("refund added to invoice", message);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: Invoice was already refunded.", message);
        }
    }

    @Test public void testSaleRefund() throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("comment", "test");
        params.put("category", "1");
        try {
            Sale sale = TwocheckoutSale.retrieve("9093717691800");
            TwocheckoutResponse result = sale.refund(params);
            String message = result.getResponseMessage();
            assertEquals("Refund added to invoice", message);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: Invoice was already refunded.", message);
        }
    }

    @Test public void testSaleStop() throws Exception {
        Sale sale = TwocheckoutSale.retrieve("9093717691800");
        TwocheckoutResponse result = sale.stop();
        String message = result.getResponseMessage();
        assertEquals("No active recurring lineitems.", message);
    }


    @Test public void testLineitemStop() throws Exception {
        try {
            Sale sale = TwocheckoutSale.retrieve("9093717691800");
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
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("comment", "test");
        params.put("category", "1");
        try {
            Sale sale = TwocheckoutSale.retrieve("9093717691800");
            Invoice[] invoices = sale.getInvoices();
            Invoice invoice = invoices[invoices.length-1];
            Lineitem[] lineitems = invoice.getLineitems();
            Lineitem lineitem = lineitems[0];
            TwocheckoutResponse result = lineitem.refund(params);
            String message = result.getResponseMessage();
            assertEquals("lineitem refunded", message);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: Lineitem was already refunded.", message);
        }
    }

    @Test public void testSaleComment() throws Exception {
        Sale sale = TwocheckoutSale.retrieve("9093717691800");
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("sale_comment", "test");
        TwocheckoutResponse result = sale.comment(params);
        String message = result.getResponseMessage();
        assertEquals("Created comment successfully.", message);
    }

    @Test public void testSaleShip() throws Exception {
        try {
            Sale sale = TwocheckoutSale.retrieve("9093717691800");
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("tracking_number", "test");
            TwocheckoutResponse result = sale.ship(params);
            String message = result.getResponseMessage();
            assertEquals("Sale marked shipped.", message);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: Sale already marked shipped.", message);
        }
    }

    @Test public void testSaleReauth() throws Exception {
        try {
            Sale sale = TwocheckoutSale.retrieve("9093717691800");
            TwocheckoutResponse result = sale.reauth();
            String message = result.getResponseMessage();
            assertEquals("Payment reauthorized.", message);
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: " +
                    "Payment is already pending or deposited and cannot be reauthorized.", message);
        }
    }

    @Test public void testProductCreate() throws Exception {
        try {
            HashMap<String, String> params = new HashMap<String, String>();
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
            Product product = TwocheckoutProduct.retrieve(result.getProductId());
            product_id = result.getProductId();
            String message = result.getResponseMessage();
            assertEquals("Product successfully created", message);
            assertEquals("Test Short Description", product.getDescription());
        } catch (TwocheckoutException e) {
            e.printStackTrace();
        }
    }

    @Test public void testProductRetrieve() throws Exception {
        Product product = TwocheckoutProduct.retrieve(product_id);
        assertEquals(product_id, String.valueOf(product.getProductId()));
    }

    @Test public void testProductUpdate() throws Exception {
        Product product = TwocheckoutProduct.retrieve(product_id);
        TwocheckoutResponse result = product.update();
        assertEquals("Product successfully updated", result.getResponseMessage());
    }

    @Test public void testProductDelete() throws Exception {
        Product product = TwocheckoutProduct.retrieve(product_id);
        TwocheckoutResponse result = product.delete();
        assertEquals("Product successfully deleted.", result.getResponseMessage());
    }

    @Test public void testOptionCreate() throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("option_name", "test");
        params.put("option_value_name", "test");
        params.put("option_value_surcharge", "1.00");
        TwocheckoutResponse result = TwocheckoutOption.create(params);
        option_id = result.getOptionId();
        assertEquals("Option created successfully", result.getResponseMessage());
    }

    @Test public void testOptionRetrieve() throws Exception {
        ProductOption option = TwocheckoutOption.retrieve(option_id);
        assertEquals(option_id, String.valueOf(option.getOptionId()));
    }

    @Test public void testOptionUpdate() throws Exception {
        try {
            ProductOption option = TwocheckoutOption.retrieve(option_id);
            TwocheckoutResponse result = option.update();
            assertEquals("Option updated successfully", result.getResponseMessage());
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: " +
                    "Specified option_value_id does not belong to specifed option_id.", message);
        }
    }

    @Test public void testOptionDelete() throws Exception {
        ProductOption option = TwocheckoutOption.retrieve(option_id);
        TwocheckoutResponse result = option.delete();
        assertEquals("Option deleted successfully", result.getResponseMessage());
    }

    @Test public void testCouponCreate() throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        long unixTime = System.currentTimeMillis() / 1000L;
        String code = Long.toString(unixTime);
        params.put("coupon_code", code);
        params.put("date_expire", "2020-12-12");
        params.put("type", "sale");
        params.put("minimum_purchase", "1.00");
        params.put("value_off", "0.50");
        TwocheckoutResponse result = TwocheckoutCoupon.create(params);
        coupon_code = result.getCouponCode();
        assertEquals("Coupon successfully created", result.getResponseMessage());
    }

    @Test public void testCouponRetrieve() throws Exception {
        Coupon coupon = TwocheckoutCoupon.retrieve(coupon_code);
        assertEquals(coupon_code, coupon.getCouponCode());
    }

    @Test public void testCouponUpdate() throws Exception {
        Coupon coupon = TwocheckoutCoupon.retrieve(coupon_code);
        TwocheckoutResponse result = coupon.update();
        assertEquals("Coupon updated successfully", result.getResponseMessage());
    }

    @Test public void testCouponDelete() throws Exception {
        Coupon coupon = TwocheckoutCoupon.retrieve(coupon_code);
        TwocheckoutResponse result = coupon.delete();
        assertEquals("Coupon successfully deleted.", result.getResponseMessage());
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
            HashMap<String, String> billing = new HashMap<String, String>();
            billing.put("name", "Testing Tester");
            billing.put("addrLine1", "123 Test St");
            billing.put("city", "Columbus");
            billing.put("state", "Ohio");
            billing.put("country", "USA");
            billing.put("zipCode", "43230");
            billing.put("email", "tester@2co.com");
            billing.put("phone", "555-555-5555");

            HashMap<String, Object> request = new HashMap<String, Object>();
            request.put("sellerId", "90124820");
            request.put("merchantOrderId", "test123");
            request.put("token", "Yzc0OGU0ZGItMzcxZi00MzQ5LWE2YjAtZmMzNTRjMWNiZGQ1");
            request.put("currency", "USD");
            request.put("total", "1.00");
            request.put("billingAddr", billing);

            Authorization response = TwocheckoutCharge.authorize(request);
            assertEquals("APPROVED", response.getResponseCode());
        } catch (TwocheckoutException e) {
            String message = e.toString();
            assertEquals("com.twocheckout.TwocheckoutException: " +
                    "Unauthorized", message);
        }
    }

}
