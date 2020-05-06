package com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.seoul_north.interfaces;

import com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.seoul_south.models.ResultList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface SeoulNorthRetrofitInterface {
    @GET("/districts/2")
    Call<ResultList> getDistrictsByRock(@Header("x-access-token") String acessToken);
}
