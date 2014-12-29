package com.msevgi.smarthouse.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ListView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.activity.MessageActivity;
import com.msevgi.smarthouse.adapter.BellListAdapter;
import com.msevgi.smarthouse.content.SmartHouseContentProvider;
import com.msevgi.smarthouse.event.NewBellEvent;
import com.msevgi.smarthouse.view.ExtendedFloatingActionMenu;
import com.squareup.otto.Subscribe;

import butterknife.InjectView;
import butterknife.OnClick;

public final class BellFragment extends BaseFragment implements Runnable, SnackBar.OnVisibilityChangeListener {
    public static final int POSITION = 0;

    @InjectView(R.id.fragment_bell_response_menu)
    protected ExtendedFloatingActionMenu mResponseMenu;

    @InjectView(R.id.fragment_bell_listview)
    protected ListView mListView;

    @NonNull
    @Override
    protected int getTitleResource() {
        return R.string.title_bell;
    }

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_bell;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Uri bellUri = SmartHouseContentProvider.getBellUri();
        String reverseOrder = SmartHouseContentProvider.Bell.KEY_ID + " DESC";
        Cursor cursor = getActivity().getContentResolver().query(bellUri, null, null, null, reverseOrder);
        BellListAdapter adapter = new BellListAdapter(getContext(), cursor);
        mListView.setAdapter(adapter);

        mResponseMenu.attachToListView(mListView);
    }

    @OnClick(R.id.fragment_bell_response_message)
    public void onResponseButtonClicked(View view) {
        Intent intent = new Intent(getContext(), MessageActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, "button");
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());

        mResponseMenu.postCollapse();
    }

    @Subscribe
    public void onNewBellEvent(NewBellEvent event) {
        getActivity().runOnUiThread(this);
    }

    @Override
    public void run() {
        new SnackBar
                .Builder(getActivity())
                .withMessage("Door has rang!")
                .withVisibilityChangeListener(this)
                .show();
    }

    @Override
    public void onShow(int i) {
        mResponseMenu.hide(false);
    }

    @Override
    public void onHide(int i) {
        mResponseMenu.show(false);
    }
}
