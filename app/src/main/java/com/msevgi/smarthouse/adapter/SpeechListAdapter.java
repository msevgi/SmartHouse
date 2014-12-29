package com.msevgi.smarthouse.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.content.SmartHouseContentProvider;
import com.msevgi.smarthouse.event.SpeechItemSelectEvent;
import com.msevgi.smarthouse.provider.BusProvider;
import com.msevgi.smarthouse.view.SpeechTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public final class SpeechListAdapter extends CursorAdapter {

    private LayoutInflater mInflater;
    private int mContentIndex;

    public SpeechListAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        mInflater = LayoutInflater.from(context);

        mContentIndex = cursor.getColumnIndex(SmartHouseContentProvider.Speech.KEY_CONTENT);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.cell_speech, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String speech = cursor.getString(mContentIndex);
        viewHolder.mSpeechTextView.setSpeech(speech);
        viewHolder.mSpeechTextView.setText(speech);
    }

    protected static class ViewHolder {

        @InjectView(R.id.cell_speech_textview)
        SpeechTextView mSpeechTextView;

        protected ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

        @OnClick(R.id.cell_speech_textview)
        public void onSpeechItemSelected(SpeechTextView view) {
            String speechString = view.getSpeech();
            SpeechItemSelectEvent event = new SpeechItemSelectEvent();
            event.setSpeech(speechString);
            BusProvider.getInstance().post(event);
        }
    }

}
