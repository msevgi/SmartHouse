package com.msevgi.smarthouse.interfaces;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface DoNotDisturbRestInterface {

    @GET("/donotdisturb/{value}/{id}")
    void trigger(
            @Path("value") boolean value, @Path("id") int id, Callback<Object> callback
    );

}
