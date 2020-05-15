package com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.interfaces;

import com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.models.RestaurantInfoResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RestaurantInfoRetrofitInterface {
//    @GET("/test")
@GET("/restaurants/{restaurantId}")
@Headers("Content-Type: application/json")
Call<RestaurantInfoResult> getRestaurants(@Header("x-access-token")String acessToken, @Path("restaurantId") int number);

}
