package com.msevgi.smarthouse.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.activity.SpeechActivity;
import com.msevgi.smarthouse.adapter.BellListAdapter;
import com.msevgi.smarthouse.content.SmartHouseContentProvider;

import butterknife.InjectView;
import butterknife.OnClick;

public final class BellListFragment extends BaseFragment {
    public static final int POSITION = 0;

    @InjectView(R.id.fragment_bell_list_listview)
    protected ListView mListView;

    @InjectView(R.id.fragment_bell_response_button)
    protected FloatingActionButton mResponseButton;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_bell_list;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Uri mBellUri = SmartHouseContentProvider.getBellUri();
        Cursor mCursor = getActivity().getContentResolver().query(mBellUri, null, null, null, null);
        BellListAdapter mAdapter = new BellListAdapter(getContext(), mCursor);
        mListView.setAdapter(mAdapter);

        mResponseButton.attachToListView(mListView);

        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.fragment_bell_response_button)
    public void onResponseButtonClicked(View view) {
        Intent mIntent = new Intent(getContext(), SpeechActivity.class);
        ActivityOptionsCompat mOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, "button");
        ActivityCompat.startActivity(getActivity(), mIntent, mOptions.toBundle());
    }

}
