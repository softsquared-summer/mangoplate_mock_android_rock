package com.example.mangoplate.src.home.search_restaurant.distance_selected_layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mangoplate.R;
import com.example.mangoplate.src.BaseActivity;
import com.kofigyan.stateprogressbar.StateProgressBar;

public class DistanceSelectedLayout extends BaseActivity {
    TextView mThreeHundredM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_selected_layout);


        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
        mThreeHundredM=findViewById(R.id.three_hundred_m);
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
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
        finish();
        //액티비티 애니메이션 x
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            System.out.println("TOuch outside the dialog ******************** ");
            finish();
            return false;
        }



        return false;
    }
}
