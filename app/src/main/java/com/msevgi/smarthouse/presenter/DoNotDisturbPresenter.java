package com.msevgi.smarthouse.presenter;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import com.msevgi.smarthouse.interfaces.DoNotDisturbRestInterface;
import com.msevgi.smarthouse.provider.ConfiguratorProvider;
import com.msevgi.smarthouse.provider.RestAdapterProvider;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public final class DoNotDisturbPresenter extends SwitchCompat implements CompoundButton.OnCheckedChangeListener, Callback<Object> {

    public DoNotDisturbPresenter(Context context) {
        super(context);

        if (!isInEditMode())
            init();
    }

    public DoNotDisturbPresenter(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (!isInEditMode())
            init();
    }

    public DoNotDisturbPresenter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (!isInEditMode())
            init();
    }

    private void init() {
        Boolean value = ConfiguratorProvider.getInstance(getContext()).doNotDistrub().getOr(false);
        setChecked(value);

        setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean newValue) {
        ConfiguratorProvider.getInstance(getContext()).doNotDistrub().put(newValue).commit();

        DoNotDisturbRestInterface doNotDisturbRestInterface = RestAdapterProvider.getInstance().create(DoNotDisturbRestInterface.class);
        doNotDisturbRestInterface.trigger(newValue, this);
    }

    @Override
    public void success(Object object, Response response) {

    }

    @Override
    public void failure(RetrofitError error) {

    }
}
