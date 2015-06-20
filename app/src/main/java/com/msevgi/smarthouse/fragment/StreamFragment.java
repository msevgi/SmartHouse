package com.msevgi.smarthouse.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebView;
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

public final class StreamFragment extends BaseFragment implements Callback<CameraStateResponseBean> {

    public static final int POSITION = 2;

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

        StreamRestInterface switchRestInterface = RestAdapterProvider.getInstance().create(StreamRestInterface.class);
        switchRestInterface.trigger(StreamRestInterface.ON, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        StreamRestInterface switchRestInterface = RestAdapterProvider.getInstance().create(StreamRestInterface.class);
        switchRestInterface.trigger(StreamRestInterface.OFF, this);
    }

    public void init() {
        String ipAddress = ConfiguratorProvider.getInstance(getContext()).IpAddress().getOr(ApplicationConstants.API_URL);
        String[] split = ipAddress.split(":");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("http:")
                .append(split[1])
                .append(":9000/stream");

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
}
