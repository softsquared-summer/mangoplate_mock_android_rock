package com.softSquared.mangoplate.src.home.discount.interfaces;

import com.softSquared.mangoplate.src.home.discount.models.EatDealsResponse;
import com.softSquared.mangoplate.src.home.mystatus.events.models.EventsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface EatDealsRetrofitInterface {

    @GET("/eatdeals")
    @Headers("Content-Type: application/json")
    Call<EatDealsResponse> GetEdeals(@Header("x-access-token") String acessToken);
}
