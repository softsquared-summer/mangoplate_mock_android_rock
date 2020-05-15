package com.softSquared.mangoplate.src.home.search_restaurant.interfaces;

        import com.softSquared.mangoplate.src.home.search_restaurant.models.RestaurantResultResponse;

        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Header;
        import retrofit2.http.Query;

public interface SearchRetrofitInterface {
    //    @GET("/test")
    @GET("/restaurants")
    Call<RestaurantResultResponse> getRestaurants(@Header("x-access-token") String acessToken, @Query("lat") float lat, @Query("lng") float lng, @Query("type") String type, @Query("area") String area);
    @GET("/restaurants")
    Call<RestaurantResultResponse> getStartRestaurants(@Header("x-access-token") String acessToken, @Query("lat") float lat, @Query("lng") float lng, @Query("type") String type);
}
