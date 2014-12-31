package com.msevgi.smarthouse.provider;

import android.content.Context;

import com.msevgi.smarthouse.config.Configurator;

public final class ConfiguratorProvider {
    private static Configurator sConfigurator;

    public static Configurator getInstance(Context context) {
        if (sConfigurator == null)
            sConfigurator = new Configurator(context.getSharedPreferences("Configs", context.MODE_PRIVATE));

        return sConfigurator;
    }

}
