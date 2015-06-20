package com.msevgi.smarthouse.application;

import com.msevgi.smarthouse.bean.TokenRequestBean;
import com.msevgi.smarthouse.constant.RegisterConstants;
import com.msevgi.smarthouse.event.TokenSendEvent;
import com.msevgi.smarthouse.interfaces.TokenRestInterface;
import com.msevgi.smarthouse.provider.RestAdapterProvider;
import com.msevgi.smarthouse.task.GcmRegisterAsyncTask;
import com.squareup.otto.Subscribe;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public final class SmartHouseApplication extends BaseApplication implements Callback<Object> {

    @Override
    public void onCreate() {
        super.onCreate();

        new GcmRegisterAsyncTask(this).execute();
    }

    @Subscribe
    public void onTokenSendEvent(TokenSendEvent event) {
        String token = event.getToken();

        TokenRequestBean tokenRequestBean = new TokenRequestBean();
        tokenRequestBean.setApikey(token);

        TokenRestInterface tokenRestInterface = RestAdapterProvider.getInstance().create(TokenRestInterface.class);
        tokenRestInterface.send(tokenRequestBean, this);

        RegisterConstants.setToken(token);
    }

    @Override
    public void success(Object object, Response response) {
        // TODO Log success
    }

    @Override
    public void failure(RetrofitError error) {
        // TODO Log failure
    }

}
