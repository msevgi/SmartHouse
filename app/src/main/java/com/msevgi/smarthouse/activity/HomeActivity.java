package com.msevgi.smarthouse.activity;

import android.os.Bundle;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.task.GcmRegisterAsyncTask;

import butterknife.OnClick;

public final class HomeActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btnGetRegId)
    protected void onRegisterGcmClicked() {
        GcmRegisterAsyncTask mTask = new GcmRegisterAsyncTask(this);
        mTask.execute();
    }

}
