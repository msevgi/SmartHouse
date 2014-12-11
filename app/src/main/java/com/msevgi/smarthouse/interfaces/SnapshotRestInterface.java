package com.msevgi.smarthouse.interfaces;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Streaming;

public interface SnapshotRestInterface {

    @Streaming
    @GET("/isthereanybody/")
    Response getByteArray();

}
