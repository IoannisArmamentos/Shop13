package com.shop13.Information;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shop13.R;

public class HowToOrder extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        System.out.println("Running on Create View How To Order");
        return inflater.inflate(R.layout.howtoorder, container, false);
    }

}


