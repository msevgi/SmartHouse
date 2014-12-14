package com.msevgi.smarthouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.model.NavigationItem;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public final class NavigationDrawerListAdapter extends ArrayAdapter<NavigationItem> {

    public NavigationDrawerListAdapter(Context context, List<NavigationItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_drawer, parent, false);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else
            mViewHolder = (ViewHolder) convertView.getTag();

        int mTextResourceId = getItem(position).getTextResource();
        CharSequence mText = getContext().getString(mTextResourceId);
        mViewHolder.mTextView.setText(mText);

        int mDrawableResourceId = getItem(position).getDrawableResource();
        mViewHolder.mTextView.setCompoundDrawablesWithIntrinsicBounds(mDrawableResourceId, 0, 0, 0);

        return convertView;
    }


    protected static class ViewHolder {

        @InjectView(R.id.cell_drawer_textview)
        TextView mTextView;

        protected ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}