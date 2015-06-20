package com.msevgi.smarthouse.interfaces;

import com.msevgi.smarthouse.bean.RegisterRequestBean;
import com.msevgi.smarthouse.bean.RegisterResponseBean;
import com.msevgi.smarthouse.bean.SpeechRequestBean;
import com.msevgi.smarthouse.bean.SpeechResponseBean;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface RegisterRestInterface {

    @POST("/register")
    void register(@Body RegisterRequestBean body, Callback<RegisterResponseBean> callback);
}
