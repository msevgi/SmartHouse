package com.msevgi.smarthouse.config;

import android.content.Context;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.util.AttributeSet;

import com.msevgi.smarthouse.provider.ConfiguratorProvider;

public final class SendMessageConfig extends CheckBoxPreference implements Preference.OnPreferenceChangeListener {

    public SendMessageConfig(Context context) {
        super(context);
        init();
    }

    public SendMessageConfig(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SendMessageConfig(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        boolean currentValue = ConfiguratorProvider.getInstance(getContext()).sendSMS().getOr(false);
        setChecked(currentValue);
        setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean value = (boolean) newValue;
        ConfiguratorProvider.getInstance(getContext()).sendSMS().put(value).commit();
        return true;
    }
}
