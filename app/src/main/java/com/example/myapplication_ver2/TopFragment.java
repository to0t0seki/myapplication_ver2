package com.example.myapplication_ver2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class TopFragment extends Fragment {
    ViewModelA viewModelA;
     int i=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setBackgroundColor(Color.RED);
        viewModelA = ViewModelProviders.of(getActivity()).get(ViewModelA.class);
        container.addView(linearLayout);
        Button button = new Button(getContext());
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                viewModelA.upDateMachineList(getContext());
            }
        });
        linearLayout.addView(button);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
