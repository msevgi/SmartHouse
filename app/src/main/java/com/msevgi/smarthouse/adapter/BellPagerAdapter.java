package com.msevgi.smarthouse.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.fragment.BellListSubFragment;
import com.msevgi.smarthouse.fragment.LastBellSubFragment;

public final class BellPagerAdapter extends FragmentStatePagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
    private static final int PAGE_COUNT = 2;

    private Context mContext;

    public BellPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
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

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case BellListSubFragment.SUB_POSITION:
                return "Bell List";
            case LastBellSubFragment.SUB_POSITION:
                return "Last Bell";
            default:
                throw new IllegalArgumentException("Must return a title");
        }

    }

    @Override
    public int getPageIconResId(int position) {
        switch (position) {
            case BellListSubFragment.SUB_POSITION:
                return R.drawable.ic_list;
            case LastBellSubFragment.SUB_POSITION:
                return R.drawable.ic_bell;
            default:
                throw new IllegalArgumentException("Must return a icon");
        }
    }
}
