package com.msevgi.smarthouse.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.content.SmartHouseContentProvider;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public final class BellListAdapter extends CursorAdapter {

    private LayoutInflater mInflater;
    private int mTimeIndex;
    private int mUrlIndex;

    public BellListAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        mInflater = LayoutInflater.from(context);

        mTimeIndex = cursor.getColumnIndex(SmartHouseContentProvider.Bell.KEY_TIME);
        mUrlIndex = cursor.getColumnIndex(SmartHouseContentProvider.Bell.KEY_URL);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View mView = mInflater.inflate(R.layout.cell_bell, parent, false);
        ViewHolder mHolder = new ViewHolder(mView);
        mView.setTag(mHolder);

        return mView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder mViewHolder = (ViewHolder) view.getTag();

        String mTime = cursor.getString(mTimeIndex);
        mViewHolder.mDateTextView.setText(mTime);

        String mUrl = cursor.getString(mUrlIndex);
        Picasso
                .with(context)
                .load(mUrl)
                .into(mViewHolder.mImageView);
    }

    protected static class ViewHolder {

        @InjectView(R.id.cell_bell_photo_imageview)
        ImageView mImageView;

        @InjectView(R.id.cell_bell_time_textview)
        TextView mDateTextView;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
