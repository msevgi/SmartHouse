package com.msevgi.smarthouse.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.event.NavigationItemSelectEvent;
import com.msevgi.smarthouse.model.NavigationItem;
import com.msevgi.smarthouse.provider.BusProvider;

import java.util.List;

public final class NavigationDrawerListAdapter extends RecyclerView.Adapter<NavigationDrawerListAdapter.ViewHolder> {

    private List<NavigationItem> mData;
    private Context mContext;
    private int mSelectedPosition;
    private int mTouchedPosition = -1;

    public NavigationDrawerListAdapter(List<NavigationItem> data, Context context) {
        mContext = context;
        mData = data;
    }

    @Override
    public NavigationDrawerListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_drawer, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NavigationDrawerListAdapter.ViewHolder viewHolder, final int position) {
        int mTextResourceId = mData.get(position).getTextResource();
        String mTitle = mContext.getResources().getString(mTextResourceId);
        viewHolder.textView.setText(mTitle);
        viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(mData.get(position).getDrawable(), null, null, null);
        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
                                                   @Override
                                                   public boolean onTouch(View v, MotionEvent event) {

                                                       switch (event.getAction()) {
                                                           case MotionEvent.ACTION_DOWN:
                                                               touchPosition(position);
                                                               return false;
                                                           case MotionEvent.ACTION_CANCEL:
                                                               touchPosition(-1);
                                                               return false;
                                                           case MotionEvent.ACTION_MOVE:
                                                               return false;
                                                           case MotionEvent.ACTION_UP:
                                                               touchPosition(-1);
                                                               return false;
                                                       }
                                                       return true;
                                                   }
                                               }
        );
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       NavigationItemSelectEvent mEvent = new NavigationItemSelectEvent();
                                                       mEvent.setPosition(position);
                                                       BusProvider.getInstance().post(mEvent);
                                                   }
                                               }
        );

        //TODO: selected menu position, change layout accordingly
        if (mSelectedPosition == position || mTouchedPosition == position) {
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.color_selected_navigation));
        } else {
            viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void touchPosition(int position) {
        int lastPosition = mTouchedPosition;
        mTouchedPosition = position;
        if (lastPosition >= 0)
            notifyItemChanged(lastPosition);
        if (position >= 0)
            notifyItemChanged(position);
    }

    public void selectPosition(int position) {
        int lastPosition = mSelectedPosition;
        mSelectedPosition = position;
        notifyItemChanged(lastPosition);
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }
}