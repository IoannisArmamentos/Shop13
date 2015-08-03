package com.shop13;

import android.app.Activity;
import android.app.Fragment;
/*import android.support.v4.app.FragmentManager;
import android.app.FragmentManager;*/
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends Fragment implements NavigationDrawerCallbacks {


    private FragmentActivity myContext;
    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;
    private View mFragmentContainerView;

    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read in the flag indicating whether or not the user has demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mDrawerList = (RecyclerView) view.findViewById(R.id.drawerList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDrawerList.setLayoutManager(layoutManager);
        mDrawerList.setHasFixedSize(true);

        final List<NavigationItem> navigationItems = getMenu();
        NavigationDrawerAdapter adapter = new NavigationDrawerAdapter(navigationItems);
        adapter.setNavigationDrawerCallbacks(this);
        mDrawerList.setAdapter(adapter);
        selectItem(mCurrentSelectedPosition);
        return view;
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        return mActionBarDrawerToggle;
    }

    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        //NavigationDrawer epilegoyme kapoia kartela kai anoigei to antistoixo fragment.Ousiastika einai ena menu
        Fragment fragment = null ; // Vazoume null gt petaei sfalma an dn to kathorisoume apo tin arxi kai kapoio case den to exei
                                   // Mas ta kanein dld karpouzia kai planites antistoixa.
        android.app.FragmentManager fragmentManager = getFragmentManager(); // For AppCompat use getSupportFragmentManager
        switch (position) {
            default:
            case 0: //Products
                getActivity().setTitle(Build.MODEL + " | " + "Προϊόντα ");

                FragmentManager fragManager = myContext.getSupportFragmentManager();
                ProductFragment fragmentV4;
               // android.support.v4.app.FragmentManager fragmentManagerV4 = getSupportFragmentManager();
                fragmentV4 = new ProductFragment();
                selectItem(position); //Xreiazetai gia na klinei to drawer
                fragManager.beginTransaction() //Ksekinaei to fragment pou dialextike
                        .replace(R.id.container, fragmentV4)
                        .commit();
                break;
            case 1: //Shop13
                getActivity().setTitle("Shop13");
                fragment = new About();
                selectItem(position); //Xreiazetai gia na klinei to drawer
                fragmentManager.beginTransaction() //Ksekinaei to fragment pou dialextike
                        .replace(R.id.container, fragment)
                        .commit();
                break;
            case 2: //HowToOrder
                getActivity().setTitle("Πως παραγγέλνω");
                fragment = new HowToOrder();
                selectItem(position); //Xreiazetai gia na klinei to drawer
                fragmentManager.beginTransaction() //Ksekinaei to fragment pou dialextike
                        .replace(R.id.container, fragment)
                        .commit();
                break;
            case 3: //Exit
                fragment = new Exit();
                selectItem(position); //Xreiazetai gia na klinei to drawer
                fragmentManager.beginTransaction() //Ksekinaei to fragment pou dialextike
                        .replace(R.id.container, fragment)
                        .commit();
                break;
        }

    }

    public List<NavigationItem> getMenu() {
        //ArrayList me antikeimena,diladi ta fragments
        List<NavigationItem> items = new ArrayList<NavigationItem>();
        items.add(new NavigationItem("Συμβατά Προϊόντα", getResources().getDrawable(R.drawable.ic_shopping_music_grey600_36dp)));
        items.add(new NavigationItem("Shop13", getResources().getDrawable(R.drawable.ic_phone_grey600_36dp)));
        items.add(new NavigationItem("Πως παραγγέλνω", getResources().getDrawable(R.drawable.ic_help_circle_grey600_36dp)));
        items.add(new NavigationItem("Έξοδος", getResources().getDrawable(R.drawable.ic_exit_to_app_grey600_36dp)));
        return items;
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     * @param toolbar      The Toolbar of the activity.
     */
    public void setup(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        // To setup tis mparas kai tou NavDrawer
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.myPrimaryDarkColor));

        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) return;

                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) return;
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }
                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }

    private void selectItem(int position) {
        //Epilogi Antikeimenou kai thesimo thesis
        mCurrentSelectedPosition = position;
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
        ((NavigationDrawerAdapter) mDrawerList.getAdapter()).selectPosition(position);
    }

    public void openDrawer() {
        mDrawerLayout.openDrawer(mFragmentContainerView);
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(mFragmentContainerView);
    }

    @Override
    public void onAttach(Activity activity) {
        // To pire
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        // Kill it with fire pls
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

}
