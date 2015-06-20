package com.msevgi.smarthouse.bean;

public final class RegisterRequestBean {
    private String msisdn;
    private String token;

    public RegisterRequestBean() {
    }

    public String getMSISDN() {
        return msisdn;
    }

    public void setMSISDN(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
