package com.softSquared.mangoplate.src.home.search_restaurant.interfaces;

import com.softSquared.mangoplate.src.home.search_restaurant.models.RestaurantResultResponse;

public interface SearchRestaurantViewFragment {

    void validateSuccess(String text);

    void validateFailure(String message);

    void successUpdateRecyclerView(RestaurantResultResponse restaurantInfoResultList);
}
