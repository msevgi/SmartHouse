package com.msevgi.smarthouse.interfaces;

import com.msevgi.smarthouse.bean.SpeechRequestBean;
import com.msevgi.smarthouse.bean.SpeechResponseBean;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface SpeechRestInterface {

    @POST("/textToSpeech")
    void send(@Body SpeechRequestBean body, Callback<SpeechResponseBean> callback);
}
