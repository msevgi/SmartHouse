package com.msevgi.smarthouse.interfaces;

import com.msevgi.smarthouse.bean.TokenRequestBean;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface TokenRestInterface {

    @POST("/newapikey")
    void send(@Body TokenRequestBean body, Callback<TokenRequestBean> callback);
}
