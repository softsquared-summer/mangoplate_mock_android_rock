package com.softSquared.mangoplate.src.home.mystatus.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.softSquared.mangoplate.R;

public class EventsActivity extends AppCompatActivity {
    private GridLayoutManager mGridLayoutManager;
    private EventsRecyclerAdapter madapter;
    RecyclerView mRecyclerViewEvents;
    ImageView backkey_events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        evnets_tryGet(); // events 목록 API 통신 (GET)

        backkey_events=findViewById(R.id.backkey_events);
        backkey_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void evnets_tryGet() {

        final EventsService mainService = new EventsService(this);
        mainService.tryEventsGet();
    }


    //코드만 봐도 바로 알 수 있게


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
