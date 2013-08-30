//package com.twocheckout;
//
//import com.twocheckout.model.*;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.util.HashMap;
//
//import static junit.framework.Assert.assertEquals;
//
//public class TwocheckoutTest {
//
//    @BeforeClass public static void setUp() {
//        Twocheckout.apiusername = "APIuser1817037";
//        Twocheckout.apipassword = "APIpass1817037";
//    }
//
//    static String product_id;
//    static String option_id;
//    static String coupon_code;
//
//    @Test public void testSaleRetrieve() throws Exception {
//        try {
//            Sale sale = TwocheckoutSale.retrieve("4774380224");
//            String sale_id = String.valueOf(sale.getSaleId());
//            assertEquals("4774380224", sale_id);
//        } catch (TwocheckoutException e) {
//            String message = e.toString();
//            assertEquals("com.twocheckout.TwocheckoutException: Unable to find record.", message);
//        }
//    }
//
//    @Test public void testInvoiceRetrieve() throws Exception {
//        try {
//            Sale sale = TwocheckoutInvoice.retrieve("4832573658");
//            String sale_id = String.valueOf(sale.getSaleId());
//            assertEquals("4832573649", sale_id);
//        } catch (TwocheckoutException e) {
//            String message = e.toString();
//            assertEquals("com.twocheckout.TwocheckoutException: Unable to find record.", message);
//        }
//    }
//
//    @Test public void testInvoiceRefund() throws Exception {
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("comment", "test");
//        params.put("category", "1");
//        try {
//            Sale sale = TwocheckoutInvoice.retrieve("4832573658");
//            TwocheckoutResponse result = sale.refund(params);
//            String message = result.getResponseMessage();
//            assertEquals("refund added to invoice", message);
//        } catch (TwocheckoutException e) {
//            String message = e.toString();
//            assertEquals("com.twocheckout.TwocheckoutException: Invoice was already refunded.", message);
//        }
//    }
//
//    @Test public void testSaleRefund() throws Exception {
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("comment", "test");
//        params.put("category", "1");
//        try {
//            Sale sale = TwocheckoutSale.retrieve("4774380224");
//            TwocheckoutResponse result = sale.refund(params);
//            String message = result.getResponseMessage();
//            assertEquals("Refund added to invoice", message);
//        } catch (TwocheckoutException e) {
//            String message = e.toString();
//            assertEquals("com.twocheckout.TwocheckoutException: Invoice too old to refund.", message);
//        }
//    }
//
//    @Test public void testSaleStop() throws Exception {
//        Sale sale = TwocheckoutSale.retrieve("4832772521");
//        TwocheckoutResponse result = sale.stop();
//        String message = result.getResponseMessage();
//        assertEquals("No active recurring lineitems.", message);
//    }
//
//
//    @Test public void testLineitemStop() throws Exception {
//        try {
//            Sale sale = TwocheckoutSale.retrieve("4832772521");
//            Invoice[] invoices = sale.getInvoices();
//            Invoice invoice = invoices[invoices.length-1];
//            Lineitem[] lineitems = invoice.getLineitems();
//            Lineitem lineitem = lineitems[0];
//            TwocheckoutResponse result = lineitem.stop();
//            String message = result.getResponseMessage();
//            assertEquals("Recurring billing stopped for lineitem", message);
//        } catch (TwocheckoutException e) {
//            String message = e.toString();
//            assertEquals("com.twocheckout.TwocheckoutException: Lineitem is not scheduled to recur.", message);
//        }
//    }
//
//    @Test public void testLineitemRefund() throws Exception {
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("comment", "test");
//        params.put("category", "1");
//        try {
//            Sale sale = TwocheckoutSale.retrieve("4832772521");
//            Invoice[] invoices = sale.getInvoices();
//            Invoice invoice = invoices[invoices.length-1];
//            Lineitem[] lineitems = invoice.getLineitems();
//            Lineitem lineitem = lineitems[0];
//            TwocheckoutResponse result = lineitem.refund(params);
//            String message = result.getResponseMessage();
//            assertEquals("lineitem refunded", message);
//        } catch (TwocheckoutException e) {
//            String message = e.toString();
//            assertEquals("com.twocheckout.TwocheckoutException: Lineitem was already refunded.", message);
//        }
//    }
//
//    @Test public void testSaleComment() throws Exception {
//        Sale sale = TwocheckoutSale.retrieve("4832772521");
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("sale_comment", "test");
//        TwocheckoutResponse result = sale.comment(params);
//        String message = result.getResponseMessage();
//        assertEquals("Created comment successfully.", message);
//    }
//
//    @Test public void testSaleShip() throws Exception {
//        try {
//            Sale sale = TwocheckoutSale.retrieve("4831096515");
//            HashMap<String, String> params = new HashMap<String, String>();
//            params.put("tracking_number", "test");
//            TwocheckoutResponse result = sale.ship(params);
//            String message = result.getResponseMessage();
//            assertEquals("Sale marked shipped.", message);
//        } catch (TwocheckoutException e) {
//            String message = e.toString();
//            assertEquals("com.twocheckout.TwocheckoutException: Item not shippable.", message);
//        }
//    }
//
//    @Test public void testSaleReauth() throws Exception {
//        try {
//            Sale sale = TwocheckoutSale.retrieve("4831096515");
//            TwocheckoutResponse result = sale.reauth();
//            String message = result.getResponseMessage();
//            assertEquals("Payment reauthorized.", message);
//        } catch (TwocheckoutException e) {
//            String message = e.toString();
//            assertEquals("com.twocheckout.TwocheckoutException: " +
//                    "Payment is already pending or deposited and cannot be reauthorized.", message);
//        }
//    }
//
//    @Test public void testProductCreate() throws Exception {
//        try {
//            HashMap<String, String> params = new HashMap<String, String>();
//            params.put("name", "test");
//            params.put("price", "1.00");
//            params.put("vendor_product_id", "Test");
//            params.put("description", "Test Short Description");
//            params.put("long_description", "Test Long Description");
//            params.put("approved_url", "http://www.2checkout.com");
//            params.put("tangible", "1");
//            params.put("weight", "1.50");
//            params.put("handling", "1.00");
//            params.put("recurring", "1");
//            params.put("recurrence", "1 Month");
//            params.put("duration", "Forever");
//            TwocheckoutResponse result = TwocheckoutProduct.create(params);
//            Product product = TwocheckoutProduct.retrieve(result.getProductId());
//            product_id = result.getProductId();
//            String message = result.getResponseMessage();
//            assertEquals("Product successfully created", message);
//            assertEquals("Test Short Description", product.getDescription());
//        } catch (TwocheckoutException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test public void testProductRetrieve() throws Exception {
//        Product product = TwocheckoutProduct.retrieve(product_id);
//        assertEquals(product_id, String.valueOf(product.getProductId()));
//    }
//
//    @Test public void testProductUpdate() throws Exception {
//        Product product = TwocheckoutProduct.retrieve(product_id);
//        TwocheckoutResponse result = product.update();
//        assertEquals("Product successfully updated", result.getResponseMessage());
//    }
//
//    @Test public void testProductDelete() throws Exception {
//        Product product = TwocheckoutProduct.retrieve(product_id);
//        TwocheckoutResponse result = product.delete();
//        assertEquals("Product successfully deleted.", result.getResponseMessage());
//    }
//
//    @Test public void testOptionCreate() throws Exception {
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("option_name", "test");
//        params.put("option_value_name", "test");
//        params.put("option_value_surcharge", "1.00");
//        TwocheckoutResponse result = TwocheckoutOption.create(params);
//        option_id = result.getOptionId();
//        assertEquals("Option created successfully", result.getResponseMessage());
//    }
//
//    @Test public void testOptionRetrieve() throws Exception {
//        ProductOption option = TwocheckoutOption.retrieve(option_id);
//        assertEquals(option_id, String.valueOf(option.getOptionId()));
//    }
//
//    @Test public void testOptionUpdate() throws Exception {
//        try {
//            ProductOption option = TwocheckoutOption.retrieve(option_id);
//            TwocheckoutResponse result = option.update();
//            assertEquals("Option updated successfully", result.getResponseMessage());
//        } catch (TwocheckoutException e) {
//            String message = e.toString();
//            assertEquals("com.twocheckout.TwocheckoutException: " +
//                    "Specified option_value_id does not belong to specifed option_id.", message);
//        }
//    }
//
//    @Test public void testOptionDelete() throws Exception {
//        ProductOption option = TwocheckoutOption.retrieve(option_id);
//        TwocheckoutResponse result = option.delete();
//        assertEquals("Option deleted successfully", result.getResponseMessage());
//    }
//
//    @Test public void testCouponCreate() throws Exception {
//        HashMap<String, String> params = new HashMap<String, String>();
//        long unixTime = System.currentTimeMillis() / 1000L;
//        String code = Long.toString(unixTime);
//        params.put("coupon_code", code);
//        params.put("date_expire", "2020-12-12");
//        params.put("type", "sale");
//        params.put("minimum_purchase", "1.00");
//        params.put("value_off", "0.50");
//        TwocheckoutResponse result = TwocheckoutCoupon.create(params);
//        coupon_code = result.getCouponCode();
//        assertEquals("Coupon successfully created", result.getResponseMessage());
//    }
//
//    @Test public void testCouponRetrieve() throws Exception {
//        Coupon coupon = TwocheckoutCoupon.retrieve(coupon_code);
//        assertEquals(coupon_code, coupon.getCouponCode());
//    }
//
//    @Test public void testCouponUpdate() throws Exception {
//        Coupon coupon = TwocheckoutCoupon.retrieve(coupon_code);
//        TwocheckoutResponse result = coupon.update();
//        assertEquals("Coupon updated successfully", result.getResponseMessage());
//    }
//
//    @Test public void testCouponDelete() throws Exception {
//        Coupon coupon = TwocheckoutCoupon.retrieve(coupon_code);
//        TwocheckoutResponse result = coupon.delete();
//        assertEquals("Coupon successfully deleted.", result.getResponseMessage());
//    }
//
//}
