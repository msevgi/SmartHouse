package com.msevgi.smarthouse.config;

import android.content.Context;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.widget.Toast;

import com.msevgi.smarthouse.bean.RegisterRequestBean;
import com.msevgi.smarthouse.bean.RegisterResponseBean;
import com.msevgi.smarthouse.constant.RegisterConstants;
import com.msevgi.smarthouse.interfaces.RegisterRestInterface;
import com.msevgi.smarthouse.provider.ConfiguratorProvider;
import com.msevgi.smarthouse.provider.RestAdapterProvider;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public final class MSISDNConfig extends EditTextPreference implements Preference.OnPreferenceChangeListener, Callback<RegisterResponseBean> {

    public MSISDNConfig(Context context) {
        super(context);
        init();
    }

    public MSISDNConfig(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MSISDNConfig(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        String currentValue = ConfiguratorProvider.getInstance(getContext()).MSISDN().getOr("");
        setText(currentValue);
        setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String value = (String) newValue;
        ConfiguratorProvider.getInstance(getContext()).MSISDN().put(value).commit();

        RegisterRequestBean registerRequestBean = new RegisterRequestBean();
        registerRequestBean.setMSISDN(value);
        registerRequestBean.setToken(RegisterConstants.getToken());

        RegisterRestInterface registerRestInterface = RestAdapterProvider.getInstance().create(RegisterRestInterface.class);
        registerRestInterface.register(registerRequestBean, this);

        return true;
    }

    @Override
    public void success(RegisterResponseBean registerResponseBean, Response response) {
        int id = registerResponseBean.getId();
        RegisterConstants.setID(id);

        Toast.makeText(getContext(), "Register completed successfully.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(getContext(), "Register failed.", Toast.LENGTH_LONG).show();
    }
}