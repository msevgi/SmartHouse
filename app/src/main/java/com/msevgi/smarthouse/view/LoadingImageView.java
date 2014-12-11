package com.msevgi.smarthouse.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.msevgi.smarthouse.R;
import com.pnikosis.materialishprogress.ProgressWheel;

public class LoadingImageView extends FrameLayout {
    private ImageView mImageView;
    private ProgressWheel mProgressWhell;

    public LoadingImageView(Context context) {
        super(context);
        if (!isInEditMode())
            init();
    }

    public LoadingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            init();
    }

    public LoadingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            init();
    }

    private void init() {
        inflate(getContext(), R.layout.segment_loading_imageview, this);

        mImageView = (ImageView) findViewById(R.id.segment_loading_imageview_imageview);
        mProgressWhell = (ProgressWheel) findViewById(R.id.segment_loading_imageview_progress);
    }

    public void setImageBitmap(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }

    public void showProgress() {
        mProgressWhell.setVisibility(VISIBLE);
    }

    public void hideProgress() {
        mProgressWhell.setVisibility(GONE);
    }
}
