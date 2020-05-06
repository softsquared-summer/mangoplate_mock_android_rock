package com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.models.RestaurantInfoService;

public class RetaurantInformationLayout extends AppCompatActivity {

    RestaurantInfoService restaurantInfoService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retaurant_information);
        restaurantInfoService =new RestaurantInfoService(this);
        restaurantInfoService.tryGetRestaurantInfoList();
    }
}
