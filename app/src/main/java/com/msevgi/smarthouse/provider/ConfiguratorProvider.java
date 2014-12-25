package com.msevgi.smarthouse.provider;

import android.content.Context;

import com.msevgi.smarthouse.config.Configurator;

public final class ConfiguratorProvider {
    private static Configurator mConfigurator;

    public static Configurator getInstance(Context context) {
        if (mConfigurator == null)
            mConfigurator = new Configurator(context.getSharedPreferences("Configs", context.MODE_PRIVATE));

        return mConfigurator;
    }

}
