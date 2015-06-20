package com.msevgi.smarthouse.interfaces;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface DoNotDisturbRestInterface {

    @GET("/donotdisturb/{value}")
    void trigger(
            @Path("value") boolean value, Callback<Object> callback
    );

}
