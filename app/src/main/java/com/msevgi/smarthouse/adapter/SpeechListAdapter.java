package com.msevgi.smarthouse.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private int mSpeechIndex;

    public SpeechListAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        mInflater = LayoutInflater.from(context);

        mSpeechIndex = cursor.getColumnIndex(SmartHouseContentProvider.Speech.KEY_SPEECH);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View mView = mInflater.inflate(R.layout.cell_speech, parent, false);
        ViewHolder mHolder = new ViewHolder(mView);
        mView.setTag(mHolder);

        return mView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder mViewHolder = (ViewHolder) view.getTag();

        String mSpeech = cursor.getString(mSpeechIndex);
        mViewHolder.mSpeechTextView.setSpeech(mSpeech);
        mViewHolder.mSpeechTextView.setText(mSpeech);
    }

    protected static class ViewHolder {

        @InjectView(R.id.cell_speech_textview)
        SpeechTextView mSpeechTextView;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

        @OnClick(R.id.cell_speech_textview)
        public void onSpeechItemSelected(SpeechTextView view) {
            String mSpeech = view.getSpeech();
            SpeechItemSelectEvent mEvent = new SpeechItemSelectEvent();
            mEvent.setSpeech(mSpeech);
            BusProvider.getInstance().post(mEvent);
        }
    }

}
