package com.shop13.Tabs;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shop13.R;

public class ProtectorsFragment extends Fragment
{

   /* @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }*/

    private ListView listView;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.protectors_fragment,container,false);
        listView = (ListView) root.findViewById(R.id.list);
        listView.setAdapter(AllProductsFragment.adapterProtector);
        System.out.println("Running on Create View protectors");

        return root;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    /*private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }*/
}