package com.softSquared.mangoplate.src.home.naverMapActivity.models;

import java.util.List;

public class RestaurantMapResultList {
    List<RestaurantMapResult> result;
    boolean isSuccess;
    int code;
    String message;

    public List<RestaurantMapResult> getResult() {
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
