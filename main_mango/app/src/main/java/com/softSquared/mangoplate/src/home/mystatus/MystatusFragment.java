package com.softSquared.mangoplate.src.home.mystatus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.home.mystatus.events.EventsActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MystatusFragment extends Fragment {
    ViewGroup mRootView;
    ImageView mEventsTab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_mystatus, container, false);
        mEventsTab = mRootView.findViewById(R.id.event_tab);
        mEventsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveEvent = new Intent(getActivity(), EventsActivity.class);
                startActivity(moveEvent);
            }
        });
        return mRootView;
    }
}
