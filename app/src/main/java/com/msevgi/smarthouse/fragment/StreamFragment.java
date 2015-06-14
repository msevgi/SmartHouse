package com.msevgi.smarthouse.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.msevgi.smarthouse.R;

import butterknife.InjectView;

public final class StreamFragment extends BaseFragment implements View.OnClickListener {

    public static final int POSITION = 3;

    @InjectView(R.id.fragment_stream_webview)
    protected WebView mWebView;

    @InjectView(R.id.fragment_stream_url)
    protected EditText mUrlEditText;

    @InjectView(R.id.fragment_stream_watch)
    protected Button mButtonGo;

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

        mButtonGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("http://")
                .append(mUrlEditText.getText().toString())
                .append(":9000/stream");

        mWebView.setInitialScale(100);
        mWebView.loadUrl(stringBuilder.toString());
    }
}
