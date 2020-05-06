package com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.models;

import java.util.List;

public class RestaurantInfoResult {


    private RestaurantInfoResultList result;
    private Boolean isSuccess;
    private Integer code;
    private String message;

    public RestaurantInfoResultList getResult() {
        return result;
    }

    public void setResult(RestaurantInfoResultList result) {
        this.result = result;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
/**
 * {
 * "result": {
 * "images": [
 * {
 * "imageId": 24,
 * "imageUrl": "https://i.imgur.com/F7DJZXK.jpg"
 * },
 * {
 * "imageId": 25,
 * "imageUrl": "https://i.imgur.com/up1Bh1Y.jpg"
 * },
 * {
 * "imageId": 26,
 * "imageUrl": "https://i.imgur.com/X9p3A1y.jpg"
 * }
 * ],
 * "name": "춘천옥",
 * "seenNum": "206",
 * "reviewNum": "1",
 * "starNum": "0",
 * "rating": "3.3",
 * "ratingColor": "gray",
 * "userStar": "NO",
 * "address": "서울특별시 금천구 디지털로12길 19 2층",
 * "oldAddress": "서울 금천구 가산동 140-38",
 * "phone": "02-868-9937",
 * "userName": "EunJin♡",
 * "userProfileUrl": "https://i.imgur.com/0kOW0oe.jpg",
 * "infoUpdate": "마지막 업데이트: 2017-07-19",
 * "infoTime": "11:50 - 21:30",
 * "infoHoliday": null,
 * "infoDescription": "시즌마다 메뉴가 바뀔 수 있어요. 전화 후 방문해주세요.",
 * "infoPrice": "만원 - 2만원",
 * "infoKind": "한식",
 * "infoParking": "무료주차 가능",
 * "infoSite": "",
 * "keywords": [
 * {
 * "keyword": "#막국수"
 * },
 * {
 * "keyword": "#보쌈"
 * },
 * {
 * "keyword": "#보쌈김치"
 * }
 * ],
 * "menu": [
 * {
 * "name": "메밀 막국수",
 * "price": "8,000"
 * },
 * {
 * "name": "보쌈小",
 * "price": "29,000"
 * },
 * {
 * "name": "선지국밥",
 * "price": "7,000"
 * }
 * ],
 * "menuUpdate": "마지막 업데이트: 2020-05-05"
 * },
 * "isSuccess": true,
 * "code": 200,
 * "message": "식당 상세 조회"
 * }
 */