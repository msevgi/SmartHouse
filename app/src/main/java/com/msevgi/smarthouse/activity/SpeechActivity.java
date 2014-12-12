package com.msevgi.smarthouse.activity;

import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
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

    private SpeechListAdapter mAdapter;
    private MaterialDialog mDialog;

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
        mAdapter = new SpeechListAdapter(this, mCursor);
        mDialog = new MaterialDialog.Builder(this)
                .title("Choose a template")
                .adapter(mAdapter)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_speech, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_template:
                mDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onSpeechItemSelected(SpeechItemSelectEvent event) {
        String mSpeech = event.getSpeech();
        mResponseEditText.setText(mSpeech);
        mResponseEditText.setSelection(mSpeech.length());
        mDialog.dismiss();
    }

    @OnClick(R.id.activity_speech_accept_button)
    public void onAcceptButtonClicked() {
        String mSpeechString = mResponseEditText.getText().toString();

        SpeechRequestBean mRequestBean = new SpeechRequestBean(mSpeechString);
        mRequestBean.setLanguage("EN");

        SpeechRestInterface mResponseInterface = RestAdapterProvider.getInstance().create(SpeechRestInterface.class);
        mResponseInterface.send(mRequestBean, this);

        SmartHouseContentProvider.Speech mSpeech = new SmartHouseContentProvider.Speech();
        mSpeech.setContent(mSpeechString);

        Uri mSpeechUri = SmartHouseContentProvider.getSpeechUri();
        getContentResolver().insert(mSpeechUri, mSpeech.toContentValues());
    }

    @Override
    public void success(SpeechResponseBean speechResponseBean, Response response) {
        onBackPressed();
    }

    @Override
    public void failure(RetrofitError error) {

    }

    @Override
    public void onBackPressed() {
        AndroidUtil.hideKeyboard(this);
        super.onBackPressed();
    }

}
