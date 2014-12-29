package com.msevgi.smarthouse.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.adapter.NavigationDrawerListAdapter;
import com.msevgi.smarthouse.event.NavigationItemSelectEvent;
import com.msevgi.smarthouse.helper.NavigationHelper;
import com.msevgi.smarthouse.model.NavigationItem;
import com.msevgi.smarthouse.provider.BusProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnItemClick;

public final class NavigationDrawerFragment extends BaseFragment {

    @InjectView(R.id.fragment_navigation_drawer_list)
    protected ListView mDrawerList;

    private View mFragmentContainerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<NavigationItem> navigationItems = getMenu();
        NavigationDrawerListAdapter adapter = new NavigationDrawerListAdapter(getContext(), navigationItems);
        mDrawerList.setAdapter(adapter);
    }

    @OnItemClick(R.id.fragment_navigation_drawer_list)
    public void onDrawerListItemSelected(int position) {
        NavigationItemSelectEvent event = new NavigationItemSelectEvent();
        event.setPosition(position);
        BusProvider.getInstance().post(event);

        closeDrawer();
        mDrawerList.setItemChecked(position, true);
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

    public void navigate(int position) {
        NavigationItemSelectEvent event = new NavigationItemSelectEvent();
        event.setPosition(position);
        BusProvider.getInstance().post(event);

        mDrawerList.setItemChecked(position, true);
    }

    public void openDrawer() {
        mDrawerLayout.openDrawer(mFragmentContainerView);
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(mFragmentContainerView);
    }

    private List<NavigationItem> getMenu() {
        List<NavigationItem> items = new ArrayList<NavigationItem>();
        items.add(new NavigationItem(R.string.title_bell, R.drawable.ic_home));
        items.add(new NavigationItem(R.string.title_snapshot, R.drawable.ic_snapshot));
        items.add(new NavigationItem(R.string.title_settings, R.drawable.ic_settings));
        return items;
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

}