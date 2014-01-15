package com.twocheckout.model;

import java.math.BigDecimal;

public class Lineitems {
    private String type;
    private String name;
    private String description;
    private String duration;
    private BigDecimal price;
    private int quantity;
    private String recurrence;
    private BigDecimal startupFee;
    private String tangible;
    private String productId;
    private Options[] options;

    public String getType() { return type; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDuration() { return duration; }
    public BigDecimal getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getRecurrence() { return recurrence; }
    public BigDecimal getStartupFee() { return startupFee; }
    public String getTangible() { return tangible; }
    public String getProductId() { return productId; }
    public Options[] getOptions() { return options; }
}
