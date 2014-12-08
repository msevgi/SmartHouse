package com.msevgi.smarthouse.fragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.adapter.BellListAdapter;
import com.msevgi.smarthouse.content.SmartHouseContentProvider;

import butterknife.InjectView;

public final class BellListSubFragment extends BaseFragment {

    public static final int SUB_POSITION = 0;

    @InjectView(R.id.fragment_bell_list_listview)
    protected ListView mListView;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_bell_list;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Uri mBellUri = SmartHouseContentProvider.getBellUri();
        String mReverseOrder = SmartHouseContentProvider.Bell.KEY_ID + " DESC";
        Cursor mCursor = getActivity().getContentResolver().query(mBellUri, null, null, null, mReverseOrder);
        BellListAdapter mAdapter = new BellListAdapter(getContext(), mCursor);
        mListView.setAdapter(mAdapter);

        super.onViewCreated(view, savedInstanceState);
    }

}
