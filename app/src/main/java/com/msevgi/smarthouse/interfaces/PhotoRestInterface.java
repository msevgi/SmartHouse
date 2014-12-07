package com.msevgi.smarthouse.interfaces;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Streaming;

public interface PhotoRestInterface {

    @Streaming
    @GET("/visitorphoto/{id}")
    Response getByteArray(
            @Path("id") String mId
    );
}