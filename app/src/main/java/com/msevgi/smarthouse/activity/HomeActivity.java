package com.msevgi.smarthouse.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.fragment.NavigationDrawerFragment;
import com.msevgi.smarthouse.interfaces.NavigationDrawerCallbacks;

import butterknife.InjectView;

public final class HomeActivity extends BaseActivity implements NavigationDrawerCallbacks {

    @InjectView(R.id.activity_home_toolbar)
    protected Toolbar mToolbar;

    @InjectView(R.id.activity_home_drawerlayout)
    protected DrawerLayout mDrawerLayout;

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.layout_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar = (Toolbar) findViewById(R.id.activity_home_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.activity_home_fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.activity_home_fragment_drawer, mDrawerLayout, mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }

}
