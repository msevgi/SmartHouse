package com.msevgi.smarthouse.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.msevgi.smarthouse.R;

import java.io.IOException;

import butterknife.InjectView;

public class HomeActivity extends BaseActivity implements OnClickListener {

    @InjectView(R.id.btnGetRegId)
    Button btnRegId;
    @InjectView(R.id.etRegId)
    EditText etRegId;

    GoogleCloudMessaging gcm;
    String regid;
    String PROJECT_NUMBER = "43979242575";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnRegId.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_home;
    }

    public void getRegId() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid = gcm.register(PROJECT_NUMBER);
                    msg = "Device registered, registration ID=" + regid;
                    Log.i("GCM", msg);

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();

                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                etRegId.setText(msg + "\n");
            }
        }.execute(null, null, null);
    }

    @Override
    public void onClick(View v) {
        getRegId();
    }
}
