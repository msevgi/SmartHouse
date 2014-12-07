package com.msevgi.smarthouse.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    private LayoutInflater mInflater;
    private int mTimeIndex;
    private int mBitmapIndex;

    public BellListAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        mInflater = LayoutInflater.from(context);

        mTimeIndex = cursor.getColumnIndex(BellContentProvider.Bell.KEY_TIME);
        mBitmapIndex = cursor.getColumnIndex(BellContentProvider.Bell.KEY_BITMAP);
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

        byte[] mByteArray = cursor.getBlob(mBitmapIndex);
        Bitmap mBitmap = BitmapFactory.decodeByteArray(mByteArray, 0, mByteArray.length);
        mViewHolder.mImageView.setImageBitmap(mBitmap);
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
