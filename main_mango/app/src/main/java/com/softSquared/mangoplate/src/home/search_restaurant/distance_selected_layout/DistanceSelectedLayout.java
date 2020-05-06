package com.softSquared.mangoplate.src.home.search_restaurant.distance_selected_layout;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.BaseActivity;

public class DistanceSelectedLayout extends BaseActivity {
    TextView mThreeHundredM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_selected_layout);

        setWindow(); // 액티비티가 다이얼로그로 변할 때 위치 세팅;


        mThreeHundredM = findViewById(R.id.three_hundred_m);
        mThreeHundredM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", "3");
                setResult(RESULT_OK, intent);
                finish();
                //                출처: https://ghj1001020.tistory.com/9 [혁준 블로그]
            }
        });

    }
    private void setWindow()
    {
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);


    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
        finish();
        //액티비티 애니메이션 x
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            System.out.println("TOuch outside the dialog ******************** ");
            finish();
            return false;
        }


        return false;
    }
}
