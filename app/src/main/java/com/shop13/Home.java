package com.shop13;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Home extends Fragment {
    //Ftiaxnei neo MainActivity kai ta fortwnei ola apo tin arxi
    //Den einai apodotiko gia ti mnimi,prepei na diorthwthei o kwdikas

   /* public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.activity_main,container,false);
    }*/
   /*public void onCreate(Bundle savedInstanceState)
   {
       super.onCreate(null);
       new MainActivity();
   }*/

   /* @Override
    public void onResume() {
        super.onResume();
        this.onCreate(null);
        new MainActivity();
    }*/
}


    /*public class Home extends Fragment {
        // Log tag
        private static final String TAG = MainActivity.class.getSimpleName();

        // Products json url
        private static final String url = "http://www.shop13.gr/app_search.php";

        private ProgressDialog pDialog;
        private List<Product> productList = new ArrayList<Product>();
        *//*private List<Product> caseList = new ArrayList<Product>();
        private List<Product> protectorList = new ArrayList<Product>();
        private List<Product> partsList = new ArrayList<Product>();
        private List<Product> chargeList = new ArrayList<Product>();*//*
        String caseType = "144", protectorType = "176", partsType = "174", chargeType = "180";
        private ListView listView;
        private CustomListAdapter adapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

            return inflater.inflate(R.layout.activity_main,container,false);

            listView = (ListView) getView().findViewById(R.id.list);
            adapter = new CustomListAdapter(productList);
            listView.setAdapter(adapter);

            pDialog = new ProgressDialog(this);
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
								*//*JSONArray genreArry = obj.getJSONArray("genre");
								ArrayList<String> genre = new ArrayList<String>();
								for (int j = 0; j < genreArry.length(); j++) {
									genre.add((String) genreArry.get(j));
								}
								product.setGenre(genre);*//*

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
        }*/

