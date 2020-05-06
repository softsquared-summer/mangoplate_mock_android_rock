package com.softSquared.mangoplate.src.main.models;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {
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