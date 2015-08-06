package com.shop13.DrawerActions;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.shop13.MainActivity;
import com.shop13.R;
import com.shop13.Tabs.AllProductsFragment;
import com.shop13.Tabs.CasesFragment;
import com.shop13.Tabs.ChargersFragment;
import com.shop13.Tabs.ProtectorsFragment;
import com.shop13.adapter.CustomListAdapter;
import com.shop13.app.AppController;
import com.shop13.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {


    public static TabHost mTabHost;
    private ViewPager mViewPager;
    private TabsAdapter mTabsAdapter;
    //**********************************/
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Products json url
    static String device = Build.MODEL;
    private static final String urlStr = "http://www.shop13.gr/app_search.php?model=" + device;

    private ProgressDialog pDialog;
    private List<Product> productList = new ArrayList<Product>();
    private List<Product> caseList = new ArrayList<Product>();
    private List<Product> protectorList = new ArrayList<Product>();
    private List<Product> chargeList = new ArrayList<Product>();

    private ListView listView;
    public static CustomListAdapter adapter, adapterCase, adapterProtector, adapterCharger;
    boolean flagCase = true, flagProtector = true, flagCharger = true;

    public ProductFragment() {
    }

    @Override
    public void onCreate(Bundle instance) {
        super.onCreate(instance);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.product_fragment, container, false);
        mTabHost = (TabHost) v.findViewById(android.R.id.tabhost);
        mTabHost.setup();

        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        mTabsAdapter = new TabsAdapter(getActivity(), mTabHost, mViewPager);

        mTabsAdapter.addTab(mTabHost.newTabSpec("one").setIndicator(getResources().getString(R.string.all)), AllProductsFragment.class, null);
        JSONProducts();


        return v;
    }

    public static class TabsAdapter extends FragmentPagerAdapter implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
        private final Context mContext;
        private final TabHost mTabHost;
        private final ViewPager mViewPager;
        private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

        static final class TabInfo {
            private final String tag;
            private final Class<?> clss;
            private final Bundle args;

            TabInfo(String _tag, Class<?> _class, Bundle _args) {
                tag = _tag;
                clss = _class;
                args = _args;
            }
        }

        static class DummyTabFactory implements TabHost.TabContentFactory {
            private final Context mContext;

            public DummyTabFactory(Context context) {
                mContext = context;
            }

            public View createTabContent(String tag) {
                View v = new View(mContext);
                v.setMinimumWidth(0);
                v.setMinimumHeight(0);
                return v;
            }
        }

        public TabsAdapter(FragmentActivity activity, TabHost tabHost, ViewPager pager) {
            super(activity.getSupportFragmentManager());
            mContext = activity;
            mTabHost = tabHost;
            mViewPager = pager;
            mTabHost.setOnTabChangedListener(this);
            mViewPager.setAdapter(this);
            mViewPager.setOnPageChangeListener(this);
        }

        public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
            tabSpec.setContent(new DummyTabFactory(mContext));
            String tag = tabSpec.getTag();
            TabInfo info = new TabInfo(tag, clss, args);
            mTabs.add(info);
            mTabHost.addTab(tabSpec);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

        @Override
        public Fragment getItem(int position) {
            TabInfo info = mTabs.get(position);
                        return Fragment.instantiate(mContext, info.clss.getName(), info.args);

        }

        public void onTabChanged(String tabId) {
            int position = mTabHost.getCurrentTab();
            mViewPager.setCurrentItem(position);
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            // Unfortunately when TabHost changes the current tab, it kindly
            // also takes care of putting focus on it when not in touch mode.
            // The jerk.
            // This hack tries to prevent this from pulling focus out of our
            // ViewPager.
            TabWidget widget = mTabHost.getTabWidget();
            int oldFocusability = widget.getDescendantFocusability();
            widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
            mTabHost.setCurrentTab(position);
            widget.setDescendantFocusability(oldFocusability);
        }

        public void onPageScrollStateChanged(int state) {
        }
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

    private void JSONProducts() {
        final String url = urlStr.replaceAll(" ", "%20");

        adapter = new CustomListAdapter(getActivity(), productList);
        adapterCase = new CustomListAdapter(getActivity(), caseList);
        adapterProtector = new CustomListAdapter(getActivity(), protectorList);
        adapterCharger = new CustomListAdapter(getActivity(), chargeList);
        //adapterParts = new CustomListAdapter(getActivity(), partsList);

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
                                product.setPrice(obj.getString("price"));
                                product.setSiteUrl(obj.getString("url"));
                                product.setType(obj.getString("type"));
                                product.setBuyUrl(obj.getString("urlbtn"));
                                product.setShipping(obj.getString("metaf"));
                                product.setDelivery(obj.getString("antik"));

                                // adding product to products array
                                productList.add(product);

                                //TO REMEMBER THE TYPES caseType="144", protectorType="176", partsType="174", chargeType="180";
                                /*if (product.getType().equals("144")) {
                                    caseList.add(product);
                                    if (flagCase) {
                                        mTabsAdapter.addTab(mTabHost.newTabSpec("two").setIndicator(getResources().getString(R.string.cases)), CasesFragment.class, null);
                                    }
                                    flagCase = false;
                                } else if (product.getType().equals("176")) {
                                    protectorList.add(product);
                                    if (flagProtector) {
                                        mTabsAdapter.addTab(mTabHost.newTabSpec("three").setIndicator(getResources().getString(R.string.protectos)), ProtectorsFragment.class, null);
                                    }
                                    flagProtector = false;
                                } else if (product.getType().equals("180")) {
                                    chargeList.add(product);
                                    if (flagCharger) {
                                        mTabsAdapter.addTab(mTabHost.newTabSpec("four").setIndicator(getResources().getString(R.string.parts)), ChargersFragment.class, null);
                                    }
                                    flagCharger = false;
                                }*/
                                flagCase = addProductToCat(product,"144", getResources().getString(R.string.cases),caseList,flagCase,CasesFragment.class);
                                flagProtector = addProductToCat(product,"176",getResources().getString(R.string.protectos),protectorList,flagProtector,ProtectorsFragment.class);
                                flagCharger = addProductToCat(product,"180",getResources().getString(R.string.chargers),chargeList,flagCharger,ChargersFragment.class);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                        adapterCase.notifyDataSetChanged();
                        adapterProtector.notifyDataSetChanged();
                        adapterCharger.notifyDataSetChanged();
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

        /*final TabWidget tw = (TabWidget)mTabHost.findViewById(android.R.id.tabs);
        System.out.println("Hello ===============> " + tw.getChildCount());
        for(int i=0; i<tw.getChildCount(); i++)
        {

            final View tabView = tw.getChildTabViewAt(i);
            final TextView tv = (TextView)tabView.findViewById(android.R.id.title);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

        }*/
       /* TextView x = (TextView) mTabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        x.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);*/
        //System.out.println("=====>Fuck you");
    }

    /*product = proion pou prostheto stin lista
      catNumber = noumero katigorias pou prostethete.
      catName = onoma listas pou tha fainetai sto tab
      catList = lista pou tha ginetai add to product
      flagCat = elegxei ean uparxei i katigoria san tab
      className = pou tha kanei redirect to tab
     */
    private boolean addProductToCat(Product product, String catNumber, String catName, List<Product> catList, boolean flagCat, Class className )
    {
        if (product.getType().equals(catNumber)) {
            catList.add(product);
            if (flagCat) {
                mTabsAdapter.addTab(mTabHost.newTabSpec("Tab_").setIndicator(catName), className, null);
                for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++)
                {
                    TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    //tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
                    tv.setHorizontallyScrolling(true);
                }
            }
            flagCat = false;
        }
        return flagCat;
    }
}
