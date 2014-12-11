package com.msevgi.smarthouse.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.interfaces.SnapshotRestInterface;
import com.msevgi.smarthouse.provider.RestAdapterProvider;

import java.io.InputStream;

import butterknife.InjectView;
import retrofit.client.Response;

public final class SnapshotFragment extends BaseFragment {
    public static final int POSITION = 1;

    @InjectView(R.id.fragment_snapshot_imageview)
    protected ImageView mImageView;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_snapshot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StrictMode.ThreadPolicy mPolicy = new StrictMode.ThreadPolicy
                .Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(mPolicy);
        getSnapshot();
    }

    public void getSnapshot() {
        try {
            SnapshotRestInterface mSnapshotRestInterface = RestAdapterProvider.getInstance().create(SnapshotRestInterface.class);
            Response mResponse = mSnapshotRestInterface.getByteArray();
            InputStream mInputStream = mResponse.getBody().in();
            Bitmap mBitmap = BitmapFactory.decodeStream(mInputStream);
            mImageView.setImageBitmap(mBitmap);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("HATA", e.getMessage());
        }
    }

}
