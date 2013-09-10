package com.twocheckout.model;

public class Categories {
    private int category_id;
    private String name;
    private int parent_id;
    private String description;
    private String parent_name;

    public int getCategoryId() {
        return category_id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getParentId() {
        return parent_id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getParentName() {
        return parent_name;
    }
}
