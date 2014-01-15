package com.twocheckout.model;

import java.math.BigDecimal;

public class Options {
    private String optName;
    private String optValue;
    private BigDecimal optSurcharge;

    public String getName() { return optName; }
    public String getValue() { return optValue; }
    public BigDecimal getSurcharge() { return optSurcharge; }
}