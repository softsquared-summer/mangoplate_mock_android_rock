package com.example.mangoplate.src.home.models;

import com.google.gson.annotations.SerializedName;

public class HomeResponse {
    @SerializedName("result")
    private Result result;

    @SerializedName("code")
    private int code;

    public Result getResult() {
        return result;
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