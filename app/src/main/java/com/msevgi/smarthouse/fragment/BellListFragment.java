package com.msevgi.smarthouse.fragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.adapter.BellListAdapter;
import com.msevgi.smarthouse.bean.SpeechRequestBean;
import com.msevgi.smarthouse.bean.SpeechResponseBean;
import com.msevgi.smarthouse.content.BellContentProvider;
import com.msevgi.smarthouse.interfaces.ResponseRestInterface;
import com.msevgi.smarthouse.provider.RestAdapterProvider;

import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public final class BellListFragment extends BaseFragment implements Callback<SpeechResponseBean> {
    public static final int POSITION = 0;

    @InjectView(R.id.fragment_bell_list_listview)
    protected ListView mListView;

    @InjectView(R.id.fragment_bell_response_button)
    protected FloatingActionButton mResponseButton;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_bell_list;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Uri mBellUri = BellContentProvider.getUri();
        Cursor mCursor = getActivity().getContentResolver().query(mBellUri, null, null, null, null);
        BellListAdapter mAdapter = new BellListAdapter(getContext(), mCursor);
        mListView.setAdapter(mAdapter);

        mResponseButton.attachToListView(mListView);

        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.fragment_bell_response_button)
    public void onResponseButtonClicked() {
        SpeechRequestBean mRequestBean = new SpeechRequestBean("Enescim beğendin mi iyi konuşuyor muyum acayip tekerleme okurum!");
        ResponseRestInterface mResponse = RestAdapterProvider.getInstance().create(ResponseRestInterface.class);
        mResponse.postJson(mRequestBean, this);

        Toast.makeText(getContext(), "Response aktivite acilicak", Toast.LENGTH_LONG).show();
    }

    @Override
    public void success(SpeechResponseBean responseBean, Response response) {

    }

    @Override
    public void failure(RetrofitError error) {

    }
}
