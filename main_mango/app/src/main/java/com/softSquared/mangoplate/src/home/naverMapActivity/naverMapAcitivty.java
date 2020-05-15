package com.softSquared.mangoplate.src.home.naverMapActivity;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;
import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.BaseActivity;
import com.softSquared.mangoplate.src.home.naverMapActivity.interfaces.SearchMapRetrofitInterface;
import com.softSquared.mangoplate.src.home.naverMapActivity.models.RestaurantMapResult;
import com.softSquared.mangoplate.src.home.naverMapActivity.models.RestaurantMapResultList;
import com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.RetaurantInformationLayout;

import java.util.ArrayList;
import java.util.List;

import static com.softSquared.mangoplate.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softSquared.mangoplate.src.ApplicationClass.getRetrofit;
import static com.softSquared.mangoplate.src.ApplicationClass.lat;
import static com.softSquared.mangoplate.src.ApplicationClass.lng;

public class naverMapAcitivty extends BaseActivity implements NaverMap.OnMapClickListener, Overlay.OnClickListener, OnMapReadyCallback, NaverMap.OnCameraChangeListener, NaverMap.OnCameraIdleListener{

    private static final int ACCESS_LOCATION_PERMISSION_REQUEST_CODE = 100;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private InfoWindow infoWindow;
    private List<Marker> markerList = new ArrayList<Marker>();
    private boolean isCameraAnimated = false;
    int mRestaurantId;
    RestaurantMapResultList mRestaurantMapResultList;
    RetaurantInformationLayout retaurantInformationLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver_map_acitivty);

        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("nwwapl1yrq"));
        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);

        mapFragment.getMapAsync(this);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.grey_500));
        }
    }

    @Override
    public void onCameraChange(int reason, boolean animated) {
        isCameraAnimated = animated;
    }

    @Override
    public void onCameraIdle() {
        if (isCameraAnimated) {
            LatLng mapCenter = naverMap.getCameraPosition().target;
//            fetchStoreSale(mapCenter.latitude, mapCenter.longitude, 5000);
        }
    }



    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

        locationSource = new FusedLocationSource(this, ACCESS_LOCATION_PERMISSION_REQUEST_CODE);
        naverMap.setLocationSource(locationSource);



        tryGetRestaurantsList();
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setZoomControlEnabled(false);
        uiSettings.setLocationButtonEnabled(true);

        naverMap.addOnCameraChangeListener(this);
        naverMap.addOnCameraIdleListener(this);
        naverMap.setOnMapClickListener(this);

        LocationOverlay locationOverlay = naverMap.getLocationOverlay();
        locationOverlay.setVisible(true);
        locationOverlay.setCircleRadius(10000);
        LatLng mapCenter = naverMap.getCameraPosition().target;


        infoWindow = new InfoWindow();
        infoWindow.setAdapter(new InfoWindow.DefaultViewAdapter(this) {
            @NonNull
            @Override
            protected View getContentView(@NonNull InfoWindow infoWindow) {
                Marker marker = infoWindow.getMarker();
                RestaurantMapResult mapResult = (RestaurantMapResult) marker.getTag();
                View view = View.inflate(naverMapAcitivty.this, R.layout.recyclerview_restaurantlist, null);


                 String area;
                 String title;
                 String distance;
                 String seenNum;
                 String reviewNum;
                 float rating;
                 String ratingColor;


                Intent mMoveIntent;
                TextView title_res;
                TextView area_res;
                TextView seenNum_res;
                TextView reviewNum_res;
                TextView rating_res;


                ImageView img =view.findViewById(R.id.img_restarant);
                Glide.with(naverMapAcitivty.this).load(mapResult.getImg()).placeholder(R.drawable.loading).into(img);

                title_res = view.findViewById(R.id.title_restarant);
                area_res = view.findViewById(R.id.area_restaurant); // 얘는 settext 할 때 거리를 붙여야함
                seenNum_res = view.findViewById(R.id.seen_num_restarant);
                reviewNum_res = view.findViewById(R.id.review_num_restarant);
                rating_res = view.findViewById(R.id.rating_restarant);


                title_res.setText("" + mapResult.getTitle());
                area_res.setText("" + mapResult.getArea() + "-" + mapResult.getDistance());
                seenNum_res.setText("" + mapResult.getSeenNum());
                reviewNum_res.setText("" + mapResult.getReviewNum());
                rating_res.setText("" + mapResult.getRating());
                mRestaurantId=mapResult.getRestaurantId();
                Log.e("뭐가 문제요", "" + mapResult.getRatingColor());
                if (mapResult.getRatingColor().equals("gray")) {
                    rating_res.setTextColor(Color.parseColor("#757575"));

                } else if (mapResult.getRatingColor().equals("orange")) {
                    rating_res.setTextColor(Color.parseColor("#FF7101"));

                }
                return view;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case ACCESS_LOCATION_PERMISSION_REQUEST_CODE:
                locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults);
                return;
        }
    }


    void tryGetRestaurantsList() {

        final SearchMapRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchMapRetrofitInterface.class);



        searchRetrofitInterface.getRestaurants(X_ACCESS_TOKEN, (float) lat, (float) lng, "main").enqueue(new Callback<RestaurantMapResultList>() {
            @Override
            public void onResponse(Call<RestaurantMapResultList> call, Response<RestaurantMapResultList> response) {
                mRestaurantMapResultList = response.body();

                if (mRestaurantMapResultList.getResult() != null && mRestaurantMapResultList.getResult().size() > 0) {

                        if (response.code() == 200) {

                            if (mRestaurantMapResultList.getResult() != null) {


                                Log.e("ㅇㅇ", "" + "된다");

                            }
                            updateMapMarkers(mRestaurantMapResultList);
                        } else {
                            Log.e("ㅇㅇ", "" + "안돼");
                        }


                    }


                }

            @Override
            public void onFailure(Call<RestaurantMapResultList> call, Throwable t) {

            }

//            }







        });

    }

    private void updateMapMarkers(RestaurantMapResultList result) {
        resetMarkerList();
        if (result.getResult() != null && result.getResult().size() > 0) {
            for (RestaurantMapResult mapResult : mRestaurantMapResultList.getResult()) {
                Marker marker = new Marker();
                marker.setTag(mapResult);

                Log.e("위도", "" + mapResult.getLat());
                Log.e("경도",""+mapResult.getLng());
                Log.e("뭐지?",""+mapResult.getArea());
                marker.setPosition(new LatLng(mapResult.getLat(),mapResult.getLng()));

                marker.setIcon(OverlayImage.fromResource(R.drawable.ic_on_picker));
                marker.setWidth(70);
                marker.setHeight(100);
                marker.setAnchor(new PointF(0.5f, 1.0f)); //어디로 맵핑 가운데 아래 . 궁금하면 찾아볼껏
                marker.setMap(naverMap);
                marker.setOnClickListener(this);



                markerList.add(marker);
            }

        }
    }

    private void resetMarkerList() {
        if (markerList != null && markerList.size() > 0) {
            for (Marker marker : markerList) {
                marker.setMap(null); // 마커 클리어
            }
            markerList.clear();
        }
    }

    @Override
    public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {
        if (infoWindow.getMarker() != null) {

            infoWindow.close();
        }
    }

    @Override
    public boolean onClick(@NonNull Overlay overlay) {
        if (overlay instanceof Marker) {
            Marker marker = (Marker) overlay;
            if (marker.getInfoWindow() != null) {
                infoWindow.close();
            } else {
                infoWindow.open(marker);
                infoWindow.setOnClickListener(new Overlay.OnClickListener() {
         @Override
         public boolean onClick(@NonNull Overlay overlay) {

             Intent moveIntent =new Intent( naverMapAcitivty.this,RetaurantInformationLayout.class);
             moveIntent.putExtra("restaurantId",mRestaurantId);
             startActivity(moveIntent);
//             finish();
             return false;
         }
     });
            }
            return true;
        }
        return false;
    }


}
