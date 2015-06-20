package com.msevgi.smarthouse.event;

public final class TokenSendEvent {
    private String mToken;

    public TokenSendEvent() {
    }

    public TokenSendEvent(String mToken) {
        this.mToken = mToken;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }
}
