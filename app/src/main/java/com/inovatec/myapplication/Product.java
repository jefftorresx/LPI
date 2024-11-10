package com.inovatec.myapplication;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("product_name")
    private String productName;

    @SerializedName("brands")
    private String brand;

    @SerializedName("categories")
    private String category;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("nutriments")
    private Nutriments nutriments;

    // Getters
    public String getProductName() {
        return productName;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Nutriments getNutriments() {
        return nutriments;
    }
}
