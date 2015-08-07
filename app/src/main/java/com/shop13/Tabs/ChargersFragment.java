package com.shop13.Tabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.analytics.HitBuilders;
import com.shop13.DrawerActions.ProductFragment;
import com.shop13.R;
import com.shop13.app.AppController;

public class ChargersFragment extends Fragment {
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AppController.tracker().send(new HitBuilders.EventBuilder()
                .setCategory(getActivity().getString(R.string.click))
                .setAction(getActivity().getString(R.string.tabfortistes))
                .build());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.chargers_fragment, container, false);
        listView = (ListView) root.findViewById(R.id.list);
        listView.setAdapter(ProductFragment.adapterCharger);

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}