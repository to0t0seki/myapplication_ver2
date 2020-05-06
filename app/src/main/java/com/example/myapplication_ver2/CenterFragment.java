package com.example.myapplication_ver2;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class CenterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setBackgroundColor(Color.BLUE);
        container.addView(linearLayout);
        ViewModelA viewModelA = ViewModelProviders.of(getActivity()).get(ViewModelA.class);
        viewModelA.getList().observe(getActivity(), new Observer<Map<String,String>>() {
            @Override
            public void onChanged(Map<String,String> map) {
                System.out.println(map);

            }
        });



        Button button = new Button(getContext());
        linearLayout.addView(button);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
