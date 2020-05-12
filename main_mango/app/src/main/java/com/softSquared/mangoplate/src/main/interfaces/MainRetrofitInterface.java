package com.softSquared.mangoplate.src.main.interfaces;

import com.softSquared.mangoplate.src.main.models.DefaultResponse;
import com.softSquared.mangoplate.src.main.models.SignInJwtToken;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MainRetrofitInterface {
//    @GET("/test")
//    @GET("/jwt")
//    Call<DefaultResponse> getTest();
//
//    @GET("/test/{number}")
//    Call<DefaultResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );


    @POST("/jwt")
    @Headers("Content-Type: application/json")
    Call<DefaultResponse> postTest( @Query("type")String type, @Body SignInJwtToken params);
}
