package com.twocheckout.model;

import java.math.BigDecimal;

public class ProductOptionValue {
	private long option_value_id;
	private String option_value_name;
	private BigDecimal option_value_surcharge;
	
	public long getOptionValueId() {
		return option_value_id;
	}
	public void setOptionValueId(long option_value_id) {
		this.option_value_id = option_value_id;
	}
	public String getOptionValueName() {
		return option_value_name;
	}
	public void setOptionValueName(String option_value_name) {
		this.option_value_name = option_value_name;
	}
	public BigDecimal getOptionValueSurcharge() {
		return option_value_surcharge;
	}
	public void setOptionValueSurcharge(BigDecimal option_value_surcharge) {
		this.option_value_surcharge = option_value_surcharge;
	}
	
}
