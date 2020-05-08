package com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.BaseActivity;
import com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.models.RestaurantInfoService;

public class RetaurantInformationLayout extends BaseActivity implements NaverMap.OnMapClickListener, Overlay.OnClickListener, OnMapReadyCallback, NaverMap.OnCameraChangeListener, NaverMap.OnCameraIdleListener {
    private static final int ACCESS_LOCATION_PERMISSION_REQUEST_CODE = 100;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private InfoWindow infoWindow;
    RestaurantInfoService restaurantInfoService;
    int restaurantId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retaurant_information);
        Intent receiveIntent=getIntent();
        restaurantId=receiveIntent.getIntExtra("restaurantId",restaurantId);
        restaurantInfoService =new RestaurantInfoService(this,restaurantId);
        restaurantInfoService.tryGetRestaurantInfoList();
//        NaverMapSdk.getInstance(this).setClient(
//                new NaverMapSdk.NaverCloudPlatformClient("nwwapl1yrq"));
//        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment_information);
//        mapFragment.getMapAsync(this);


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.BLACK);
        }
    }

    @Override
    public void onCameraChange(int i, boolean b) {

    }

    @Override
    public void onCameraIdle() {

    }

    @Override
    public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {

    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        locationSource = new FusedLocationSource(this, ACCESS_LOCATION_PERMISSION_REQUEST_CODE);
        naverMap.setLocationSource(locationSource);
        Marker marker = new Marker();
        marker.setPosition(new LatLng(36.763695, 127.281796));
        marker.setMap(naverMap);
        naverMap.setMapType(NaverMap.MapType.Basic);
        NaverMapOptions options = new NaverMapOptions()
                .camera(new CameraPosition(new LatLng(35.1798159, 129.0750222), 8))
                .mapType(NaverMap.MapType.Terrain);
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setZoomControlEnabled(false);
    }

    @Override
    public boolean onClick(@NonNull Overlay overlay) {
        return false;
    }
}
