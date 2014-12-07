package com.msevgi.smarthouse.interfaces;

import com.msevgi.smarthouse.bean.SpeechRequestBean;
import com.msevgi.smarthouse.bean.SpeechResponseBean;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface ResponseRestInterface {
    @POST("/textToSpeech")
    void postJson(@Body SpeechRequestBean body, Callback<SpeechResponseBean> callback);
}
