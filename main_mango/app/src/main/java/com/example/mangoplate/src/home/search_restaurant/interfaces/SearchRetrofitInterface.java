package com.example.mangoplate.src.home.search_restaurant.interfaces;

import com.example.mangoplate.src.home.search_restaurant.localSearchTab_layout.seoul_south.models.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchRetrofitInterface {
//    @GET("/test")
@GET("/districts")
Call<Result> getStoresByGeo(@Query("id") int id);

}
