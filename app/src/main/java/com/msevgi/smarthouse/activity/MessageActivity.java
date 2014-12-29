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
import com.msevgi.smarthouse.model.Language;
import com.msevgi.smarthouse.provider.RestAdapterProvider;
import com.msevgi.smarthouse.util.AndroidUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.otto.Subscribe;

import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public final class MessageActivity extends BaseActivity implements Callback<SpeechResponseBean> {

    @InjectView(R.id.activity_speech_reponse_edittext)
    protected MaterialEditText mResponseEditText;

    @InjectView(R.id.activity_message_toolbar)
    protected Toolbar mToolbar;

    private SpeechListAdapter mAdapter;
    private MaterialDialog mDialog;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.layout_message;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cancel);
        mToolbar.setTitleTextColor(Color.BLACK);

        Uri speechUri = SmartHouseContentProvider.getSpeechUri();
        Cursor cursor = getContentResolver().query(speechUri, null, null, null, null);
        mAdapter = new SpeechListAdapter(this, cursor);
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
                return true;
            case R.id.action_template:
                mDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Subscribe
    public void onSpeechItemSelected(SpeechItemSelectEvent event) {
        String speech = event.getSpeech();
        mResponseEditText.setText(speech);
        mResponseEditText.setSelection(speech.length());
        mDialog.dismiss();
    }

    @OnClick(R.id.activity_speech_accept_button)
    public void onAcceptButtonClicked() {
        String speechString = mResponseEditText.getText().toString();

        SpeechRequestBean mRequestBean = new SpeechRequestBean(speechString);
        mRequestBean.setLanguage("TR");

        SpeechRestInterface responseInterface = RestAdapterProvider.getInstance().create(SpeechRestInterface.class);
        responseInterface.send(mRequestBean, this);

        SmartHouseContentProvider.Speech speech = new SmartHouseContentProvider.Speech();
        speech.setLanguage(Language.TR);
        speech.setContent(speechString);

        Uri speechUri = SmartHouseContentProvider.getSpeechUri();
        getContentResolver().insert(speechUri, speech.toContentValues());
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
