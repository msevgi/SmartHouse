package com.msevgi.smarthouse.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;
import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.activity.SpeechActivity;
import com.msevgi.smarthouse.adapter.BellPagerAdapter;

import butterknife.InjectView;
import butterknife.OnClick;

public final class BellFragment extends BaseFragment {
    public static final int POSITION = 0;

    @InjectView(R.id.fragment_bell_response_button)
    protected FloatingActionButton mResponseButton;

    @InjectView(R.id.fragment_bell_viewpager)
    protected ViewPager mViewPager;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_bell;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BellPagerAdapter mAdapter = new BellPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    @OnClick(R.id.fragment_bell_response_button)
    public void onResponseButtonClicked(View view) {
        Intent mIntent = new Intent(getContext(), SpeechActivity.class);
        ActivityOptionsCompat mOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, "button");
        ActivityCompat.startActivity(getActivity(), mIntent, mOptions.toBundle());
    }
}
