package com.msevgi.smarthouse.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.msevgi.smarthouse.R;
import com.pnikosis.materialishprogress.ProgressWheel;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

public final class LoadingImageView extends FrameLayout {
    private ImageViewTouch mImageView;
    private ProgressWheel mProgressWheel;

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

        mImageView = (ImageViewTouch) findViewById(R.id.segment_loading_imageview_imageview);
        mProgressWheel = (ProgressWheel) findViewById(R.id.segment_loading_imageview_progress);

        mImageView.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
    }

    public void setImageBitmap(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }

    public void showProgress() {
        mProgressWheel.setVisibility(VISIBLE);
    }

    public void hideProgress() {
        mProgressWheel.setVisibility(GONE);
    }
}
