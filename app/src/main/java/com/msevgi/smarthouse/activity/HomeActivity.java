package com.msevgi.smarthouse.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.adapter.BellListAdapter;
import com.msevgi.smarthouse.content.BellContentProvider;
import com.msevgi.smarthouse.task.GcmRegisterAsyncTask;

import butterknife.InjectView;
import butterknife.OnClick;

public final class HomeActivity extends BaseActivity {

//    @InjectView(R.id.activity_home_listview)
//    protected RecyclerView mRecyclerView;

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
    }

    @OnClick(R.id.activity_home_button)
    protected void onRegisterGcmClicked() {
        GcmRegisterAsyncTask mTask = new GcmRegisterAsyncTask(this);
        mTask.execute();
    }

}
