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
import com.msevgi.smarthouse.content.BellContentProvider;

import butterknife.ButterKnife;
import butterknife.InjectView;

public final class BellListAdapter extends CursorAdapter {

    LayoutInflater mInflater;

    public BellListAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        mInflater = LayoutInflater.from(context);
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

        int mTimeIndex = cursor.getColumnIndex(BellContentProvider.Bell.KEY_TIME);
        String mTime = cursor.getString(mTimeIndex);
        mViewHolder.mDateTextView.setText(mTime);
    }

    protected static class ViewHolder {

        @InjectView(R.id.cell_bell_imageview)
        ImageView mImageView;

        @InjectView(R.id.cell_bell_textview)
        TextView mDateTextView;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
