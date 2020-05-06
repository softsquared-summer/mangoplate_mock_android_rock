package com.softSquared.mangoplate.src.home.mystatus.events.models;

public class EventsResult {
    int eventId;

    String imageUrl;

    String title;
    String status;
    String date;
    Boolean isSuccess;
    int code;
    String message;

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getEventId() {
        return eventId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
