package com.shop13;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);



        setTitle("Shop 13 | " + Build.MODEL); //Vazei sto row to keimeno

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        //Fortnwnei me to kalosirthate to Products
        Fragment fragment;
        FragmentManager fragmentManager = getFragmentManager();
        fragment = new Products();
        fragmentManager.beginTransaction() //Ksekinaei to fragment pou dialextike
                .replace(R.id.container, fragment)
                .commit();

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // Tipota de kanei,einai gia test
        // update the main content by replacing fragments
        //Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        // Otan pataei o xristis to back button kleinei to drawer
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }

    // Filter gia ta proionta
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // create manager instance after the content view is set
        SystemBarTintManager mTintManager = new SystemBarTintManager(this);
        // enable status bar tint
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setTintColor(getResources().getColor(R.color.myPrimaryDarkColor));

        //gtp entelws
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.myPrimaryDarkColor));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));

        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Gia tin panw mikri mpara,gtp kwdikas thelei apo style kanonika alla dn allazei...
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        switch (id) {
            case R.id.action_no_filter:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                window.setStatusBarColor(this.getResources().getColor(R.color.myPrimaryDarkColor));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));
                return true;
            case R.id.action_cases:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                window.setStatusBarColor(this.getResources().getColor(R.color.casesgrey));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9e9e9e")));
                return true;
            case R.id.action_batteries:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                window.setStatusBarColor(this.getResources().getColor(R.color.batteriesgreen));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4caf50")));
                return true;
            case R.id.action_screenprotectors:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                window.setStatusBarColor(this.getResources().getColor(R.color.screenprotectorsorange));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9800")));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}