package com.inovatec.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApiService {
    @GET("api/v0/product/{barcode}.json")
    Call<ProductInfo> getProductInfo(@Path("barcode") String barcode);
}
