package com.softSquared.mangoplate.src.home.mystatus.events.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventsResponse {
    @SerializedName("result")
    private List<EventsResult> result;

    public List<EventsResult> getResult() {
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