package com.msevgi.smarthouse.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.adapter.NavigationDrawerListAdapter;
import com.msevgi.smarthouse.event.NavigationItemSelectEvent;
import com.msevgi.smarthouse.helper.NavigationHelper;
import com.msevgi.smarthouse.model.NavigationItem;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public final class NavigationDrawerFragment extends BaseFragment {
    private View mFragmentContainerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @InjectView(R.id.fragment_navigation_drawer_list)
    protected RecyclerView mDrawerList;

    @NonNull
    @Override
    protected int getTitleResource() {
        return NO_ID;
    }

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_navigation_drawer;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mDrawerList.setLayoutManager(mLayoutManager);
        mDrawerList.setHasFixedSize(true);

        List<NavigationItem> mNavigationItems = getMenu();
        NavigationDrawerListAdapter mAdapter = new NavigationDrawerListAdapter(mNavigationItems);
        mDrawerList.setAdapter(mAdapter);
        selectItem(NavigationHelper.getPosition());
        super.onViewCreated(view, savedInstanceState);
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        return mActionBarDrawerToggle;
    }

    public void setActionBarDrawerToggle(ActionBarDrawerToggle actionBarDrawerToggle) {
        mActionBarDrawerToggle = actionBarDrawerToggle;
    }

    public void setup(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) return;
                getActivity().invalidateOptionsMenu();

                CharSequence mTitle = NavigationHelper.getTitle();
                getActivity().setTitle(mTitle);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) return;
                getActivity().invalidateOptionsMenu();

                getActivity().setTitle(R.string.app_name);
            }
        };

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDrawerToggle.syncState();
            }
        });
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }

    public void openDrawer() {
        mDrawerLayout.openDrawer(mFragmentContainerView);
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(mFragmentContainerView);
    }

    public List<NavigationItem> getMenu() {
        List<NavigationItem> items = new ArrayList<NavigationItem>();
        items.add(new NavigationItem("Bells", getResources().getDrawable(R.drawable.ic_home)));
        items.add(new NavigationItem("Snapshot", getResources().getDrawable(R.drawable.ic_snapshot)));
        items.add(new NavigationItem("Settings", getResources().getDrawable(R.drawable.ic_settings)));
        return items;
    }

    void selectItem(int position) {
        if (mDrawerLayout != null)
            mDrawerLayout.closeDrawer(mFragmentContainerView);

        ((NavigationDrawerListAdapter) mDrawerList.getAdapter()).selectPosition(position);
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Subscribe
    public void onNavigationDrawerItemSelected(NavigationItemSelectEvent event) {
        int position = event.getPosition();
        selectItem(position);
    }

    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        mDrawerLayout = drawerLayout;
    }
}