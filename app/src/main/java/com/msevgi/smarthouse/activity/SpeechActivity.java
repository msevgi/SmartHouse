package com.msevgi.smarthouse.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.bean.SpeechRequestBean;
import com.msevgi.smarthouse.bean.SpeechResponseBean;
import com.msevgi.smarthouse.interfaces.SpeechRestInterface;
import com.msevgi.smarthouse.provider.RestAdapterProvider;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public final class SpeechActivity extends BaseActivity implements Callback<SpeechResponseBean> {

    @InjectView(R.id.activity_speech_reponse_edittext)
    protected MaterialEditText mResponseEditText;

    @InjectView(R.id.activity_speech_toolbar)
    protected Toolbar mToolbar;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.layout_speech;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @OnClick(R.id.activity_speech_accept_button)
    public void onAcceptButtonClicked() {
        String mResponseString = mResponseEditText.getText().toString();
        SpeechRequestBean mRequestBean = new SpeechRequestBean(mResponseString);
        SpeechRestInterface mResponse = RestAdapterProvider.getInstance().create(SpeechRestInterface.class);
        mResponse.postJson(mRequestBean, this);
    }

    @Override
    public void success(SpeechResponseBean speechResponseBean, Response response) {
        onBackPressed();
    }

    @Override
    public void failure(RetrofitError error) {

    }
}
