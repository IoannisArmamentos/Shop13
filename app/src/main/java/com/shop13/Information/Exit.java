package com.shop13.Information;

import android.app.Fragment;
import android.os.Bundle;

public class Exit extends Fragment {
    //Kleinei thn efarmogh
    //Den einai apodotiko gia ti mnimi,prepei na diorthwthei o kwdikas

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        System.out.println("Alahu Akbar");
        System.exit(0);
    }
    public void onDestroy() {
        super.onDestroy();
    }
}