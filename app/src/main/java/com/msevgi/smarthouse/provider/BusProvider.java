package com.msevgi.smarthouse.provider;
import com.squareup.otto.Bus;

public final class BusProvider {

    private static Bus bus = new Bus();

    public static Bus getInstance() {
        return bus;
    }

    private BusProvider() {
    }
}