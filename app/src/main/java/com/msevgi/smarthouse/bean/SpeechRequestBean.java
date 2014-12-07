package com.msevgi.smarthouse.bean;

public final class SpeechRequestBean {
    private String message;

    public SpeechRequestBean(String message) {
        this.message = message;
    }

    public SpeechRequestBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
