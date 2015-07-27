package com.shop13;

import android.app.Fragment;
import android.os.Bundle;

public class Exit extends Fragment {
    //Kleinei thn efarmogh
    //Den einai apodotiko gia ti mnimi,prepei na diorthwthei o kwdikas

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        System.exit(0);
    }
    public void onDestroy() {
        super.onDestroy();
    }
}