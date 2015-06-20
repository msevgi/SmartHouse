package com.msevgi.smarthouse.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

public final class ExtendedFloatingActionMenu extends FloatingActionsMenu implements Runnable {
    private static final int TRANSLATE_DURATION_MILLIS = 200;

    private int mPostTime = 1000;
    private int mScrollThreshold = 10;
    private boolean mVisible = true;
    private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();

    public ExtendedFloatingActionMenu(Context context) {
        super(context);
    }

    public ExtendedFloatingActionMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtendedFloatingActionMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void postCollapse() {
        postDelayed(this, mPostTime);
    }

    @Override
    public void run() {
        collapse();
    }

    public void setPostTime(int time) {
        mPostTime = time;
    }

    public void attachToListView(@NonNull AbsListView listView) {
        attachToListView(listView, null);
    }

    private void toggleShow(final boolean visible, final boolean animate, boolean force) {
        if (mVisible != visible || force) {
            mVisible = visible;
            int height = getHeight();
            if (height == 0 && !force) {
                ViewTreeObserver vto = getViewTreeObserver();
                if (vto.isAlive()) {
                    vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            ViewTreeObserver currentVto = getViewTreeObserver();
                            if (currentVto.isAlive()) {
                                currentVto.removeOnPreDrawListener(this);
                            }
                            toggleShow(visible, animate, true);
                            return true;
                        }
                    });
                    return;
                }
            }
            int translationY = visible ? 0 : height + getMarginBottom();
            if (animate) {
                ViewPropertyAnimator.animate(this).setInterpolator(mInterpolator)
                        .setDuration(TRANSLATE_DURATION_MILLIS)
                        .translationY(translationY);
            } else {
                ViewHelper.setTranslationY(this, translationY);
            }

            // On pre-Honeycomb a translated view is still clickable, so we need to disable clicks manually
            if (!hasHoneycombApi()) {
                setClickable(visible);
            }
        }
    }

    private boolean hasLollipopApi() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    private boolean hasJellyBeanApi() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    private boolean hasHoneycombApi() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }


    public int getMarginBottom() {
        int marginBottom = 0;
        final ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginBottom = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return marginBottom;
    }

    public void show(boolean animate) {
        toggleShow(true, animate, false);
    }

    public void hide(boolean animate) {
        toggleShow(false, animate, false);
    }

    public void show() {
        show(true);
    }

    public void hide() {
        hide(true);
    }

    public void attachToListView(@NonNull AbsListView listView, ScrollDirectionListener listener) {
        AbsListViewScrollDetectorImpl scrollDetector = new AbsListViewScrollDetectorImpl();
        scrollDetector.setListener(listener);
        scrollDetector.setListView(listView);
        scrollDetector.setScrollThreshold(mScrollThreshold);
        listView.setOnScrollListener(scrollDetector);
    }

    public interface ScrollDirectionListener {
        void onScrollDown();

        void onScrollUp();
    }

    private class AbsListViewScrollDetectorImpl extends AbsListViewScrollDetector {
        private ScrollDirectionListener mListener;

        private void setListener(ScrollDirectionListener scrollDirectionListener) {
            mListener = scrollDirectionListener;
        }

        @Override
        public void onScrollDown() {
            show();
            if (mListener != null) {
                mListener.onScrollDown();
            }
        }

        @Override
        public void onScrollUp() {
            if (isExpanded())
                collapse();

            hide();
            if (mListener != null) {
                mListener.onScrollUp();
            }
        }
    }

    abstract class AbsListViewScrollDetector implements AbsListView.OnScrollListener {
        private int mLastScrollY;
        private int mPreviousFirstVisibleItem;
        private AbsListView mListView;
        private int mScrollThreshold;

        abstract void onScrollUp();

        abstract void onScrollDown();

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (isSameRow(firstVisibleItem)) {
                int newScrollY = getTopItemScrollY();
                boolean isSignificantDelta = Math.abs(mLastScrollY - newScrollY) > mScrollThreshold;
                if (isSignificantDelta) {
                    if (mLastScrollY > newScrollY) {
                        onScrollUp();
                    } else {
                        onScrollDown();
                    }
                }
                mLastScrollY = newScrollY;
            } else {
                if (firstVisibleItem > mPreviousFirstVisibleItem) {
                    onScrollUp();
                } else {
                    onScrollDown();
                }

                mLastScrollY = getTopItemScrollY();
                mPreviousFirstVisibleItem = firstVisibleItem;
            }
        }

        public void setScrollThreshold(int scrollThreshold) {
            mScrollThreshold = scrollThreshold;
        }

        public void setListView(@NonNull AbsListView listView) {
            mListView = listView;
        }

        private boolean isSameRow(int firstVisibleItem) {
            return firstVisibleItem == mPreviousFirstVisibleItem;
        }

        private int getTopItemScrollY() {
            if (mListView == null || mListView.getChildAt(0) == null) return 0;
            View topChild = mListView.getChildAt(0);
            return topChild.getTop();
        }
    }

}
