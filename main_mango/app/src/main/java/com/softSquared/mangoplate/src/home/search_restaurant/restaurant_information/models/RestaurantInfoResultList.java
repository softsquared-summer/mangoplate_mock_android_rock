package com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.models;

import java.util.List;

public class RestaurantInfoResultList {
    private List<images> images = null;
    private String name;
    private Double lat;
    private Double lng;
    private String seenNum;
    private String reviewNum;
    private String starNum;
    private String rating;
    private String ratingColor;
    private String userStar;
    private String address;
    private String oldAddress;
    private String phone;
    private int userId;
    private String userName;
    private String userProfileUrl;
    private String infoUpdate;
    private String infoTime;
    private String infoHoliday;
    private String infoDescription;
    private String infoPrice;
    private String infoKind;
    private String infoParking;
    private String infoSite;
    private List<keywords> keywords = null;


    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public String getSeenNum() {
        return seenNum;
    }

    public String getReviewNum() {
        return reviewNum;
    }

    public String getStarNum() {
        return starNum;
    }

    public int getUserId() {
        return userId;
    }

    public List<images> getImages() {
        return images;
    }

    public String getName() {
        return name;
    }


    public String getRating() {
        return rating;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public String getUserStar() {
        return userStar;
    }

    public String getAddress() {
        return address;
    }

    public String getOldAddress() {
        return oldAddress;
    }

    public String getPhone() {
        return phone;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public String getInfoUpdate() {
        return infoUpdate;
    }

    public String getInfoTime() {
        return infoTime;
    }

    public String getInfoHoliday() {
        return infoHoliday;
    }

    public String getInfoDescription() {
        return infoDescription;
    }

    public String getInfoPrice() {
        return infoPrice;
    }

    public String getInfoKind() {
        return infoKind;
    }

    public String getInfoParking() {
        return infoParking;
    }

    public String getInfoSite() {
        return infoSite;
    }

    public List<keywords> getKeywords() {
        return keywords;
    }

    public class keywords
    {
        String keyword;

        public String getKeyword() {
            return keyword;
        }
    }
    public class images{


        private Integer imageId;
        private String imageUrl;

        public Integer getImageId() {
            return imageId;
        }

        public void setImageId(Integer imageId) {
            this.imageId = imageId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

}
