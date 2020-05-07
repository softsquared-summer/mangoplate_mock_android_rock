package com.softSquared.mangoplate.src.home.naverMapActivity.models;

public class RestaurantMapResult {
    int areaId;
    String area;
    int restaurantId;

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    float lat;
    float lng;
    String img;
    String title;
    String distance;
    String seenNum;
    int reviewNum;
    float rating;
    String ratingColor;

    public String getDistance() {
        return distance;
    }

    public int getAreaId() {
        return areaId;
    }

    public String getArea() {
        return area;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getSeenNum() {
        return seenNum;
    }

    public int getReviewNum() {
        return reviewNum;
    }


    public float getRating() {
        return rating;
    }

    public String getRatingColor() {
        return ratingColor;
    }
}
