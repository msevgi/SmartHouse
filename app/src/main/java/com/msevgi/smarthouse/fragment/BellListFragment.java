package com.msevgi.smarthouse.fragment;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.adapter.BellListAdapter;
import com.msevgi.smarthouse.content.BellContentProvider;
import com.msevgi.smarthouse.event.ResponseButtonClickEvent;
import com.squareup.otto.Subscribe;

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

        super.onViewCreated(view, savedInstanceState);
    }

    @Subscribe
    public void onResponseButtonClicked(ResponseButtonClickEvent event) {
        Cursor mCursor = event.getCursor();

        Toast.makeText(getContext(), mCursor.toString(), Toast.LENGTH_SHORT).show();
    }
}
