package com.example.mangoplate.src.home.search_restaurant.interfaces;

import com.example.mangoplate.src.home.search_restaurant.localSearchTab_layout.seoul_south.models.Result;
import com.example.mangoplate.src.home.search_restaurant.localSearchTab_layout.seoul_south.models.ResultList;
import com.example.mangoplate.src.home.search_restaurant.models.RestaurantResultList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface SearchRetrofitInterface {
//    @GET("/test")
@GET("/restaurants")
Call<RestaurantResultList> getRestaurants(@Header("x-access-token")String acessToken, @Query("lat") float lat , @Query("lng") float lng, @Query("type") String type, @Query("area") String area);

}
