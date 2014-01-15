package com.twocheckout.model;

import java.math.BigDecimal;

public class Authorization {
    private String type;
    private String responseCode;
    private String currencyCode;
    private String orderNumber;
    private String transactionId;
    private String recurrentInstallmentId;
    private String responseMsg;
    private String merchantOrderId;
    private BigDecimal total;
    private Lineitems[] lineItems;

    public String getType() { return type; }
    public String getResponseCode() { return responseCode; }
    public String getCurrencyCode() { return currencyCode; }
    public String getOrderNumber() { return orderNumber; }
    public String getTransactionId() { return transactionId; }
    public String getRecurrentInstallmentId() { return recurrentInstallmentId; }
    public String getResponseMsg() { return responseMsg; }
    public String getMerchantOrderId() { return merchantOrderId; }
    public BigDecimal getTotal() { return total; }
    public Lineitems[] getLineitems() { return lineItems; }
}