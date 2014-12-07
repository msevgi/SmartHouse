package com.msevgi.smarthouse.activity;

import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.adapter.SpeechListAdapter;
import com.msevgi.smarthouse.bean.SpeechRequestBean;
import com.msevgi.smarthouse.bean.SpeechResponseBean;
import com.msevgi.smarthouse.content.SmartHouseContentProvider;
import com.msevgi.smarthouse.event.SpeechItemSelectEvent;
import com.msevgi.smarthouse.interfaces.SpeechRestInterface;
import com.msevgi.smarthouse.provider.RestAdapterProvider;
import com.msevgi.smarthouse.util.AndroidUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.otto.Subscribe;

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

    @InjectView(R.id.activity_speech_listview)
    protected ListView mListView;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.layout_speech;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cancel);
        mToolbar.setTitleTextColor(Color.BLACK);

        Uri mSpeechUri = SmartHouseContentProvider.getSpeechUri();
        Cursor mCursor = getContentResolver().query(mSpeechUri, null, null, null, null);
        SpeechListAdapter mAdapter = new SpeechListAdapter(this, mCursor);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onSpeechItemSelected(SpeechItemSelectEvent event) {
        String mSpeech = event.getSpeech();
        mResponseEditText.setText(mSpeech);
        mResponseEditText.setSelection(mSpeech.length());
    }

    @OnClick(R.id.activity_speech_accept_button)
    public void onAcceptButtonClicked() {
        String mResponseString = mResponseEditText.getText().toString();
        SpeechRequestBean mRequestBean = new SpeechRequestBean(mResponseString);
        SpeechRestInterface mResponse = RestAdapterProvider.getInstance().create(SpeechRestInterface.class);
        mResponse.postJson(mRequestBean, this);

        SmartHouseContentProvider.Speech mSpeech = new SmartHouseContentProvider.Speech();
        mSpeech.setSpeech(mResponseString);

        Uri mSpeechUri = SmartHouseContentProvider.getSpeechUri();
        getContentResolver().insert(mSpeechUri, mSpeech.toContentValues());
    }

    @Override
    public void success(SpeechResponseBean speechResponseBean, Response response) {
        AndroidUtil.hideKeyboard(this);
        onBackPressed();
    }

    @Override
    public void failure(RetrofitError error) {

    }
}
