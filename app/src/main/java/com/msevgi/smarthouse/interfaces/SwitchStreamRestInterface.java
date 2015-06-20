package com.msevgi.smarthouse.interfaces;

import com.msevgi.smarthouse.bean.CameraStateResponseBean;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface SwitchStreamRestInterface {

    int ON = 1;
    int OFF = 0;

    @GET("/stream/{switch}")
    void trigger(
            @Path("switch") int value, Callback<CameraStateResponseBean> callback
    );

}
