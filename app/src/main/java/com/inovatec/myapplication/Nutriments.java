package com.inovatec.myapplication;

import com.google.gson.annotations.SerializedName;

public class Nutriments {
    @SerializedName("energy-kcal")
    private Float calories;

    // Getters
    public Float getCalories() {
        return calories;
    }
}
