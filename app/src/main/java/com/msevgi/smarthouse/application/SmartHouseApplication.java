package com.msevgi.smarthouse.application;

import android.app.Application;
import android.widget.Toast;

import com.msevgi.smarthouse.bean.TokenRequestBean;
import com.msevgi.smarthouse.event.TokenSendEvent;
import com.msevgi.smarthouse.interfaces.TokenRestInterface;
import com.msevgi.smarthouse.provider.RestAdapterProvider;
import com.msevgi.smarthouse.task.GcmRegisterAsyncTask;
import com.squareup.otto.Subscribe;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public final class SmartHouseApplication extends BaseApplication implements Callback<TokenRequestBean> {

    @Override
    public void onCreate() {
        super.onCreate();

        new GcmRegisterAsyncTask(this).execute();
    }

    @Subscribe
    public void onTokenSendEvent(TokenSendEvent event) {
        String token = event.getmToken();

        TokenRequestBean tokenRequestBean = new TokenRequestBean();
        tokenRequestBean.setApikey(token);

        TokenRestInterface tokenRestInterface = RestAdapterProvider.getInstance().create(TokenRestInterface.class);
        tokenRestInterface.send(tokenRequestBean, this);
    }

    @Override
    public void success(TokenRequestBean tokenRequestBean, Response response) {
        // TODO Log success
    }

    @Override
    public void failure(RetrofitError error) {
        // TODO Log failure
    }
}
