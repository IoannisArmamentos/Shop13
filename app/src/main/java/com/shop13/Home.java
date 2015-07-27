package com.shop13;

import android.app.Fragment;

public class Home extends Fragment {
    //Ftiaxnei neo MainActivity kai ta fortwnei ola apo tin arxi
    //Den einai apodotiko gia ti mnimi,prepei na diorthwthei o kwdikas

    @Override
    public void onResume() {
        super.onResume();
        this.onCreate(null);
        new MainActivity();
    }
}
