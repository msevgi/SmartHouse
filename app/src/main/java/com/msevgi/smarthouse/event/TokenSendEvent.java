package com.msevgi.smarthouse.event;

public final class TokenSendEvent {
    private String mToken;

    public TokenSendEvent() {
    }

    public TokenSendEvent(String token) {
        this.mToken = token;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String mToken) {
        this.mToken = mToken;
    }
}
