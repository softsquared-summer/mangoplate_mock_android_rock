package com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.models.RestaurantInfoService;

public class RetaurantInformationLayout extends AppCompatActivity {

    RestaurantInfoService restaurantInfoService;
    int restaurantId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_restaurntinformation);
        Intent receiveIntent=getIntent();
        receiveIntent.getIntExtra("restaurantId",restaurantId);
        restaurantInfoService =new RestaurantInfoService(this,restaurantId);
        restaurantInfoService.tryGetRestaurantInfoList();
    }
}
