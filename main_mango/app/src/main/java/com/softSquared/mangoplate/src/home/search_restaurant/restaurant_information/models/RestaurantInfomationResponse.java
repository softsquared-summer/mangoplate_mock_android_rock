package com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantInfomationResponse {
    @SerializedName("result")
    private List<RestaurantInfoResult> result;

    public List<RestaurantInfoResult> getResult() {
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    @SerializedName("code")
    private int code;


    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }
}