package com.msevgi.smarthouse.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.bean.CameraStateResponseBean;
import com.msevgi.smarthouse.constant.ApplicationConstants;
import com.msevgi.smarthouse.interfaces.StreamRestInterface;
import com.msevgi.smarthouse.provider.ConfiguratorProvider;
import com.msevgi.smarthouse.provider.RestAdapterProvider;

import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public final class WebRTCFragment extends BaseFragment implements Callback<CameraStateResponseBean> {

    public static final int POSITION = 3;

    @InjectView(R.id.fragment_web_rtc_webview)
    protected WebView mWebView;

    @NonNull
    @Override
    protected int getTitleResource() {
        return R.string.title_stream;
    }

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_web_rtc;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StreamRestInterface switchRestInterface = RestAdapterProvider.getInstance().create(StreamRestInterface.class);
        switchRestInterface.trigger(StreamRestInterface.ON, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        StreamRestInterface switchRestInterface = RestAdapterProvider.getInstance().create(StreamRestInterface.class);
        switchRestInterface.trigger(StreamRestInterface.OFF, this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void init() {
        String ipAddress = ConfiguratorProvider.getInstance(getContext()).IpAddress().getOr(ApplicationConstants.API_URL);
        String[] split = ipAddress.split(":");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("http:")
                .append(split[1])
                .append(":9000/stream/webrtc");

        setUpWebViewDefaults(mWebView);
        mWebView.loadUrl(stringBuilder.toString());

        Toast.makeText(getContext(), stringBuilder.toString(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void success(CameraStateResponseBean cameraStateResponseBean, Response response) {
        int state = cameraStateResponseBean.getState();

        switch (state) {
            case StreamRestInterface.OFF:
                return;
            case StreamRestInterface.ON:
                init();
                return;
        }
    }

    @Override
    public void failure(RetrofitError error) {

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setUpWebViewDefaults(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            settings.setDisplayZoomControls(false);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        webView.setWebViewClient(new WebViewClient());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptThirdPartyCookies(webView, true);
    }
}
