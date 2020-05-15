package com.softSquared.mangoplate.src.home.search_restaurant.models;

public class RestaurantResult {
    int areaId;
    String area;
    float lat;
    float lng;
    int restaurantId;
    String img;
    String title;
    String distance;
    String seenNum;
    int reviewNum;
    String rating;
    String ratingColor;
    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public String getRating() {
        return rating;
    }



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


    public String getRatingColor() {
        return ratingColor;
    }
}
