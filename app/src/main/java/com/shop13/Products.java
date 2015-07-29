package com.shop13;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.shop13.adater.CustomListAdapter;
import com.shop13.app.AppController;
import com.shop13.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Products extends Fragment {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Products json url
    private static final String url = "http://www.shop13.gr/app_search.php";

    private ProgressDialog pDialog;
    private List<Product> productList = new ArrayList<Product>();
    /*private List<Product> caseList = new ArrayList<Product>();
    private List<Product> protectorList = new ArrayList<Product>();
    private List<Product> partsList = new ArrayList<Product>();
    private List<Product> chargeList = new ArrayList<Product>();*/
    String caseType="144", protectorType="176", partsType="174", chargeType="180";
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.products,container,false);
        listView = (ListView) root.findViewById(R.id.list);
        //kodikas gia na travaei to montelo tou kinitou
        String device = Build.MODEL;

        //getSupportActionBar().setTitle(device);

        // globally
        TextView tvDevice = (TextView) root.findViewById(R.id.device);
        tvDevice.setText(device);
        adapter = new CustomListAdapter(getActivity(), productList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest productReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Product product = new Product();
                                product.setId(((Number) obj.get("id")).intValue());
                                try {
                                    product.setName(new String(obj.getString("name").getBytes("ISO-8859-7"), "UTF-8"));
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                product.setThumbnailUrl(obj.getString("img"));
                                //product.setRating(((Number) obj.get("rating")).doubleValue());
                                product.setPrice(obj.getString("price"));
                                product.setSiteUrl(obj.getString("url"));
                                product.setType(obj.getString("type"));

                                // Genre is json array
								/*JSONArray genreArry = obj.getJSONArray("genre");
								ArrayList<String> genre = new ArrayList<String>();
								for (int j = 0; j < genreArry.length(); j++) {
									genre.add((String) genreArry.get(j));
								}
								product.setGenre(genre);*/

                                // adding product to movies array
                                productList.add(product);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(productReq);

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}