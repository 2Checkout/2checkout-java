package com.twocheckout.model;

import java.math.BigDecimal;
import java.util.HashMap;
import com.google.gson.Gson;
import com.twocheckout.TwocheckoutApi;
import com.twocheckout.TwocheckoutProduct;
import com.twocheckout.TwocheckoutResponse;

public class Product {
	private long product_id;
	private int assigned_product_id;
	private String approved_url;
	private String commission;
	private String commission_type;
	private String description;
	private String duration;
	private String handling;
	private String long_description;
	private String name;
	private String pending_url;
	private BigDecimal price;
	private String recurrence;
	private String startup_fee;
	private String tangible;
	private long vendor_id;
	private String vendor_product_id;
	private String weight;
	private Categories[] categories;
	private Image[] images;
	private ProductOption[] options;
	
	public long getProductId() {
		return product_id;
	}
	public void setProductId(long product_id) {
		this.product_id = product_id;
	}
	public int getAssignedProductId() {
		return assigned_product_id;
	}
	public void setAssignedProductId(int assigned_product_id) {
		this.assigned_product_id = assigned_product_id;
	}
	public String getApprovedUrl() {
		return approved_url;
	}
	public void setApprovedUrl(String approved_url) {
		this.approved_url = approved_url;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getCommissionType() {
		return commission_type;
	}
	public void setCommissionType(String commission_type) {
		this.commission_type = commission_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getHandling() {
		return handling;
	}
	public void setHandling(String handling) {
		this.handling = handling;
	}
	public String getLongDescription() {
		return long_description;
	}
	public void setLongDescription(String long_description) {
		this.long_description = long_description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPendingUrl() {
		return pending_url;
	}
	public void setPendingUrl(String pending_url) {
		this.pending_url = pending_url;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getRecurrence() {
		return recurrence;
	}
	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}
	public String getStartupFee() {
		return startup_fee;
	}
	public void setStartupFee(String startup_fee) {
		this.startup_fee = startup_fee;
	}
	public String getTangible() {
		return tangible;
	}
	public void setTangible(String tangible) {
		this.tangible = tangible;
	}
	public long getVendorId() {
		return vendor_id;
	}
	public void setVendorId(long vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getVendorProductId() {
		return vendor_product_id;
	}
	public void setVendorProductId(String vendor_product_id) {
		this.vendor_product_id = vendor_product_id;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public Categories[] getCategories() {
		return categories;
	}
	public void setCategories(Categories[] categories) {
		this.categories = categories;
	}
	public Image[] getImages() {
		return images;
	}
	public void setImages(Image[] images) {
		this.images = images;
	}
	public ProductOption[] getOptions() {
		return options;
	}
	public void setOptions(ProductOption[] options) {
		this.options = options;
	}
	
	public TwocheckoutResponse update() throws Exception {
		HashMap<String, String> args = new HashMap<String, String>();
		args.put("approved_url", approved_url);
		args.put("commission", commission);
		args.put("commission_type", commission_type);
		args.put("description", description);
		args.put("duration", duration);
		args.put("handling", handling);
		args.put("long_description", long_description);
		args.put("name", name);
		args.put("pending_url", pending_url);
		args.put("price", String.valueOf(price));
		args.put("product_id", String.valueOf(product_id));
		args.put("recurrence", recurrence);
		args.put("startup_fee", startup_fee);
		args.put("tangible", tangible);
		args.put("vendor_product_id", vendor_product_id);
		args.put("weight", weight);
		
		for(int i = 0;i< options.length; i++){
			args.put("option_id", String.valueOf(options[i].getOptionId()));
		}
		
		for(int i = 0;i< categories.length; i++){
			args.put("category_id", String.valueOf(categories[i].getCategoryId()));
		}
		
		String urlSuffix = "products/update_product";
		String response = TwocheckoutApi.post(urlSuffix, args);
		TwocheckoutResponse responseObj = new Gson().fromJson(response, TwocheckoutResponse.class);
		return responseObj;
	}
	
    public TwocheckoutResponse delete() throws Exception {
    	HashMap<String, String> args = new HashMap<String, String>();
    	args.put("product_id", String.valueOf(product_id));
		String urlSuffix = "products/delete_product";
		String response = TwocheckoutApi.post(urlSuffix, args);
		TwocheckoutResponse responseObj = new Gson().fromJson(response, TwocheckoutResponse.class);
		return responseObj;
    }
    
    public Product refresh() throws Exception {
    	HashMap<String, String> args = new HashMap<String, String>();
    	args.put("product_id", String.valueOf(product_id));
		String urlSuffix = "products/detail_product";
		String response = TwocheckoutApi.get(urlSuffix, args);
		TwocheckoutProduct responseObj = new Gson().fromJson(response, TwocheckoutProduct.class);
		response = new Gson().toJson(responseObj.product);
		Product resultObj = new Gson().fromJson(response, Product.class);
		return resultObj;
    }
}
