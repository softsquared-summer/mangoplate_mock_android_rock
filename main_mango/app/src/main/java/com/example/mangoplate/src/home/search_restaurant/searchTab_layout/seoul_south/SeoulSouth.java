package com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mangoplate.R;
import com.example.mangoplate.src.ApplicationClass;
import com.example.mangoplate.src.home.HomeAcitivity;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.SearchTabLayout;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south.interfaces.SeoulSouthRetrofitInterface;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south.models.Result;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south.models.ResultList;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mangoplate.src.ApplicationClass.X_ACCESS_TOKEN;

public class SeoulSouth extends Fragment {
    SearchTabLayout searchTabLayout;
    ViewGroup mView;
    int i = 0;
    int j = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = (ViewGroup) inflater.inflate(R.layout.fragment_seoul_south, container, false);
        fetchDistricts(1);
        return mView;
    }
    public SeoulSouth(SearchTabLayout searchTabLayout)
    {
        this.searchTabLayout=searchTabLayout;
    }
    private void fetchDistricts(int districts) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApplicationClass.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        SeoulSouthRetrofitInterface searchTapRetrofitInterface = retrofit.create(SeoulSouthRetrofitInterface.class);
        searchTapRetrofitInterface.getDistrictsByRock(X_ACCESS_TOKEN).enqueue(new Callback<ResultList>() {
            int checkNumber=0;// 얘는 포문 돌아가는 숫자를 세기 위해서 존재.


            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<ResultList> call, Response<ResultList> response) {
                if (response.code() == 200) {
                    ResultList resultList = response.body();
                    LayoutInflater inflater =(LayoutInflater)searchTabLayout.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    GridLayout gridLayout = (GridLayout)mView.findViewById(R.id.seoulSouth_dynamic_layout);
                    if (resultList.result != null && resultList.result.size() > 0) {
                        for (Result result : resultList.result) {
                            Log.e("성공", "" + result.id);
                            Log.e("성공", "" + result.name);



                            final boolean[] textViewFlag = new boolean[resultList.result.size()+1];// 버튼 온 오프 체크
                            for(int i=0;i<resultList.result.size()+1;i++)
                            {
                                textViewFlag[i]=true;

                            }
                            final FrameLayout frameLayout=new FrameLayout(getContext());
//                            Chip chip = new Chip(getContext());
//                            chip.setCheckable(true);
//                            chip.setChipBackgroundColorResource(R.color.white);
//                            chip.setChipStrokeColorResource(R.color.grey_300);
//                            chip.setCloseIconTintResource(R.color.grey_500);
//                            chip.setWidth(400);
//                            chip.setHeight(150);
//                            chip.setGravity(Gravity.CENTER);
//                            chip.setChipStrokeWidth(1f);
//                            chip.setText(result.name);
//                            chip.setTextColor(getResources().getColor(R.color.grey_300));
//                            chip.setRippleColorResource(R.color.grey_200);
                            final TextView textView=new TextView(getContext());
                            textView.setText(result.name);

//                            button.setId(View.generateViewId());
                            FrameLayout.LayoutParams paramsOne = new FrameLayout.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,Gravity.CENTER);

                            paramsOne.setMargins(5,10,5,10);
                            textView.setLayoutParams(paramsOne);
                            textView.setForegroundGravity(Gravity.END);
                            textView.setGravity(Gravity.CENTER);

                            textView.setId(View.generateViewId());
                            ArrayList<Integer> al = new ArrayList<Integer>();
                            al.add(View.generateViewId());
                            Log.e("id값좀 보자",""+View.generateViewId());
                            ArrayList<Boolean> checkOnOffTextView=new ArrayList<Boolean>();

                            textView.setWidth(320);
                            textView.setHeight(90);
                            textView.setTextColor(getResources().getColor(R.color.offborder));
                            textView.setBackgroundResource(R.drawable.off_rouned_border_textview);
                            final ImageView imageView=new ImageView(getContext());
                            imageView.setImageResource(R.drawable.checkimage);

                            imageView.setId(View.generateViewId());
                            int imagviewId=imageView.getId();
//                            params.setMarginStart(100);
//                            imageView.setForegroundGravity(Gravity.END| Gravity.TOP);




                            FrameLayout.LayoutParams paramsTwo = new FrameLayout.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,Gravity.TOP|Gravity.RIGHT);
//                레이아웃을 맞춰줘야 한다.
//                            https://stackoverflow.com/questions/8005526/setting-of-imageviews-gravity-to-the-center-in-android-programmatically 리니어인 경우 이 경우 참조
                            paramsTwo.setMargins(0,9,9,0);
                            imageView.setLayoutParams(paramsTwo);

                            frameLayout.setForegroundGravity(Gravity.CENTER);
                            frameLayout.addView(textView);

                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(textViewFlag[checkNumber]) {
                                    textView.setBackgroundResource(R.drawable.on_rouned_border_textview);
                                    frameLayout.addView(imageView);
                                    textView.setTextColor(getResources().getColor(R.color.Mangoplate_orange));
                                    textViewFlag[checkNumber] =false;
                                }
                                else{
                                    textView.setBackgroundResource(R.drawable.off_rouned_border_textview);
                                    ((ViewGroup)imageView.getParent()).removeView(imageView);
                                    textView.setTextColor(getResources().getColor(R.color.offborder));
                                    textViewFlag[checkNumber] =true;

                                }

                                }
                        });
                            gridLayout.setRowCount(resultList.result.size());
                            gridLayout.setColumnCount(2);
                            gridLayout.setOrientation(GridLayout.VERTICAL);


                            if (resultList.result.size() > j) {
                                if (j == 2) {
                                    j = 0;
                                    i++;


                                } else {
                                    gridLayout.addView(frameLayout, new GridLayout.LayoutParams(
                                            GridLayout.spec(i, GridLayout.CENTER,1f),
                                            GridLayout.spec(j, GridLayout.CENTER,1f) ));
                                    // weight를 줘야함
                                    Log.e("뭐하는 거야 당신",""+i+j);

                                    j++;

                                }
                                checkNumber++;

                            }
                        }

                    }
                    if(resultList.result!=null)
                    {


                    }else{

                        Log.e("실패","응");
                    }

                }
            }



            @Override
            public void onFailure(Call<ResultList> call, Throwable t) {

            }
        });
    }
}
