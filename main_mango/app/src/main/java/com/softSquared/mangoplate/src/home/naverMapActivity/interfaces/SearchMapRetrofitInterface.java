package com.softSquared.mangoplate.src.home.naverMapActivity.interfaces;

import com.softSquared.mangoplate.src.home.naverMapActivity.models.RestaurantMapResultList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface SearchMapRetrofitInterface {
    //    @GET("/test")
    @GET("/restaurants")
    Call<RestaurantMapResultList> getRestaurants(@Header("x-access-token") String acessToken, @Query("lat") float lat, @Query("lng") float lng, @Query("type") String type);
    
}
