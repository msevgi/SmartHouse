package com.msevgi.smarthouse.model;

import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;

public final class NavigationItem {
    private
    @StringRes
    int mTextResource;
    private Drawable mDrawable;

    public NavigationItem(@StringRes int textResource, Drawable drawable) {
        mTextResource = textResource;
        mDrawable = drawable;
    }

    public
    @StringRes
    int getTextResource() {
        return mTextResource;
    }

    public void setTextResource(@StringRes int textResource) {
        mTextResource = textResource;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }
}
