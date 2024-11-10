package com.inovatec.myapplication;

import com.google.gson.annotations.SerializedName;

public class ProductInfo {
    @SerializedName("product")
    private Product product;

    // Getter
    public Product getProduct() {
        return product;
    }
}
