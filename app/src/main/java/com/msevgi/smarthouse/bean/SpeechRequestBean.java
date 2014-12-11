package com.msevgi.smarthouse.bean;

public final class SpeechRequestBean {
    private String message;
    private String language;

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
