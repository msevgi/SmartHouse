package com.msevgi.smarthouse.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.adapter.BellListAdapter;
import com.msevgi.smarthouse.content.BellContentProvider;
import com.msevgi.smarthouse.task.GcmRegisterAsyncTask;

import butterknife.InjectView;
import butterknife.OnClick;

public final class HomeActivity extends BaseActivity {

    @InjectView(R.id.activity_home_listview)
    protected ListView mListView;

    private BellListAdapter mAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri mUri = BellContentProvider.getUri();
        Cursor mCursor = getContentResolver().query(mUri, null, null, null, null);
        mAdapter = new BellListAdapter(this, mCursor);
        mListView.setAdapter(mAdapter);

        ContentValues mContentValues = new ContentValues();
        mContentValues.put(BellContentProvider.Bell.KEY_TIME, "Zaman");
        getContentResolver().insert(mUri, mContentValues);
    }

    @OnClick(R.id.activity_home_button)
    protected void onRegisterGcmClicked() {
        GcmRegisterAsyncTask mTask = new GcmRegisterAsyncTask(this);
        mTask.execute();


    }

}
