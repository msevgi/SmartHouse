package com.msevgi.smarthouse.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.bean.CameraStateResponseBean;
import com.msevgi.smarthouse.constant.ApplicationConstants;
import com.msevgi.smarthouse.interfaces.SwitchStreamRestInterface;
import com.msevgi.smarthouse.provider.ConfiguratorProvider;
import com.msevgi.smarthouse.provider.RestAdapterProvider;

import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public final class WebRTCFragment extends BaseFragment implements Callback<CameraStateResponseBean> {

    public static final int POSITION = 4;

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

        SwitchStreamRestInterface switchRestInterface = RestAdapterProvider.getInstance().create(SwitchStreamRestInterface.class);
        switchRestInterface.trigger(SwitchStreamRestInterface.ON, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        SwitchStreamRestInterface switchRestInterface = RestAdapterProvider.getInstance().create(SwitchStreamRestInterface.class);
        switchRestInterface.trigger(SwitchStreamRestInterface.OFF, this);
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

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(stringBuilder.toString());

        Toast.makeText(getContext(), stringBuilder.toString(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void success(CameraStateResponseBean cameraStateResponseBean, Response response) {
        int state = cameraStateResponseBean.getState();

        switch (state) {
            case SwitchStreamRestInterface.OFF:
                return;
            case SwitchStreamRestInterface.ON:
                init();
                return;
        }
    }

    @Override
    public void failure(RetrofitError error) {

    }
}
