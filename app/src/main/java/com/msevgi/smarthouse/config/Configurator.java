package com.msevgi.smarthouse.config;

import android.content.SharedPreferences;

import com.tale.prettysharedpreferences.BooleanEditor;
import com.tale.prettysharedpreferences.PrettySharedPreferences;
import com.tale.prettysharedpreferences.StringEditor;

public final class Configurator extends PrettySharedPreferences<Configurator> {

    public Configurator(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }

    public StringEditor<Configurator> IpAddress() {
        return getStringEditor("ip_address");
    }

    public StringEditor<Configurator> MSISDN() {
        return getStringEditor("msisdn");
    }

    public BooleanEditor<Configurator> doNotDisturb() {
        return getBooleanEditor("do_not_disturb");
    }

}
