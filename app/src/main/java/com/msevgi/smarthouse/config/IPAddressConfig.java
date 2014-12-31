package com.msevgi.smarthouse.config;

import android.content.Context;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.widget.Toast;

import com.msevgi.smarthouse.constant.ApplicationConstants;
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
        String ipAddress = ApplicationConstants.API_URL;
        String currentValue = ConfiguratorProvider.getInstance(getContext()).IpAddress().getOr(ipAddress);
        setText(currentValue);
        setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String value = (String) newValue;
        ConfiguratorProvider.getInstance(getContext()).IpAddress().put(value).commit();

        Toast.makeText(getContext(), "Please restart application.", Toast.LENGTH_SHORT).show();
        return true;
    }
}