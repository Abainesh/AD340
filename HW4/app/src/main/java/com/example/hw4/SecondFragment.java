package com.example.hw4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SecondFragment extends Fragment  {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Here you can Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_second_fragment, container, false);
    }
}
