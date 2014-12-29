package com.msevgi.smarthouse.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.event.SnapshotFailureResponseEvent;
import com.msevgi.smarthouse.event.SnapshotRequestEvent;
import com.msevgi.smarthouse.event.SnapshotSuccessResponseEvent;
import com.msevgi.smarthouse.task.SnapshotAsyncTask;
import com.msevgi.smarthouse.view.LoadingImageView;
import com.squareup.otto.Subscribe;

import butterknife.InjectView;

public final class SnapshotFragment extends BaseFragment {
    public static final int POSITION = 1;

    @InjectView(R.id.fragment_snapshot_progress_imageview)
    protected LoadingImageView mLoadingImageView;

    @NonNull
    @Override
    protected int getTitleResource() {
        return R.string.title_snapshot;
    }

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_snapshot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SnapshotAsyncTask mAsyncTask = new SnapshotAsyncTask();
        mAsyncTask.execute();
    }

    @Subscribe
    public void onSnapshotRequestEvent(SnapshotRequestEvent event) {
        mLoadingImageView.showProgress();
    }

    @Subscribe
    public void onSnapshotSuccessEvent(SnapshotSuccessResponseEvent event) {
        Bitmap bitmap = event.getBitmap();
        mLoadingImageView.hideProgress();
        mLoadingImageView.setImageBitmap(bitmap);
    }


    @Subscribe
    public void onSnapshotFailureEvent(SnapshotFailureResponseEvent event) {
        mLoadingImageView.hideProgress();
    }
}
