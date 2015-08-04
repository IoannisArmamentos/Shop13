package com.shop13.Information;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shop13.R;

public class About extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        System.out.println("Running on Create View About");
        return inflater.inflate(R.layout.about,container,false);
    }
}
