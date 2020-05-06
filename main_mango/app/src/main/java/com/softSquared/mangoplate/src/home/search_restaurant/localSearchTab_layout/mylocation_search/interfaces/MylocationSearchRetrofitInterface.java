package com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.mylocation_search.interfaces;

import com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.seoul_south.models.ResultList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MylocationSearchRetrofitInterface {
    @GET("/near-districts")
    Call<ResultList> getDistrictsByRock(@Header("x-access-token") String acessToken,@Query("lat") double lat ,@Query("lng") double lng);
}
