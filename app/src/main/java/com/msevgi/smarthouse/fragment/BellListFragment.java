package com.msevgi.smarthouse.fragment;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.adapter.BellListAdapter;
import com.msevgi.smarthouse.content.BellContentProvider;

import butterknife.InjectView;

public final class BellListFragment extends BaseFragment {
    public static final int POSITION = 0;

    @InjectView(R.id.fragment_bell_list_listview)
    protected ListView mListView;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_bell_list;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Uri mBellUri = BellContentProvider.getUri();
        Cursor mCursor = getActivity().getContentResolver().query(mBellUri, null, null, null, null);
        BellListAdapter mAdapter = new BellListAdapter(getContext(), mCursor);
        mListView.setAdapter(mAdapter);

        BellContentProvider.Bell mBell = new BellContentProvider.Bell();
        mBell.setTime("Test");
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        mBell.setBitmap(bm);
        getActivity().getContentResolver().insert(mBellUri, mBell.toContentValues());

        super.onViewCreated(view, savedInstanceState);
    }
}
