package com.softSquared.mangoplate.src.home.discount.models;

public class EatDealsResult {
    private int eatdealId;
    private int areaId;
    private String imageUrl;
    private String status;
    private String percent;
    private String originalPrice;
    private String salePrice;
    private String title;
    private String item;
    private String description;
    private String quantity;

    public String getOriginalPrice() {
        return originalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getTitle() {
        return title;
    }

    public String getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }

    public String getQuantity() {
        return quantity;
    }

    public Integer getEatdealId() {
        return eatdealId;
    }

    public void setEatdealId(Integer eatdealId) {
        this.eatdealId = eatdealId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPercent() {
        return percent;
    }


}
