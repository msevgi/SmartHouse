package com.msevgi.smarthouse.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.msevgi.smarthouse.fragment.BellListSubFragment;
import com.msevgi.smarthouse.fragment.LastBellSubFragment;

public class BellPagerAdapter extends FragmentStatePagerAdapter {
    private static final int PAGE_COUNT = 2;

    public BellPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case BellListSubFragment.SUB_POSITION:
                return new BellListSubFragment();
            case LastBellSubFragment.SUB_POSITION:
                return new LastBellSubFragment();
            default:
                throw new IllegalArgumentException("Must return a fragment");
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
