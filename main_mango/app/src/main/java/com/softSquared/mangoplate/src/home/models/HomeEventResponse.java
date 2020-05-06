package com.softSquared.mangoplate.src.home.models;

import com.google.gson.annotations.SerializedName;

public class HomeEventResponse {
    @SerializedName("result")
    private HomeResult result;

    @SerializedName("code")
    private int code;

    public HomeResult getResult() {
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

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