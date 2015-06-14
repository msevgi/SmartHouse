package com.msevgi.smarthouse.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.constant.ApplicationConstants;

import butterknife.InjectView;

public final class StreamFragment extends BaseFragment {

    public static final int POSITION = 3;

    @InjectView(R.id.fragment_stream_webview)
    protected WebView mWebView;

    @NonNull
    @Override
    protected int getTitleResource() {
        return R.string.title_stream;
    }

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_stream;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }


    public void init() {
        String url = ApplicationConstants.API_URL;
        String[] split = url.split(":");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("http:")
                .append(split[1])
                .append(":9000/stream");

        mWebView.setInitialScale(100);
        mWebView.loadUrl(stringBuilder.toString());
    }
}
