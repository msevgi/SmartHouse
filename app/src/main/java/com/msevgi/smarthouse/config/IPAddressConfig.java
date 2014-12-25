package com.msevgi.smarthouse.config;

import android.content.Context;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.util.AttributeSet;

import com.msevgi.smarthouse.provider.ConfiguratorProvider;

public final class IPAddressConfig extends EditTextPreference implements Preference.OnPreferenceChangeListener {

    public IPAddressConfig(Context context) {
        super(context);
        init();
    }

    public IPAddressConfig(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IPAddressConfig(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        String mDefaultValue = ConfiguratorProvider.getInstance(getContext()).IpAddress().getOr("Def Value");
        setText(mDefaultValue);
        setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String mIPAddress = (String) newValue;
        ConfiguratorProvider.getInstance(getContext()).IpAddress().put(mIPAddress);
        return true;
    }
}
