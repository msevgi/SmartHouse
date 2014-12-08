package com.msevgi.smarthouse.event;


public final class SpeechItemSelectEvent {
    private String mSpeech;

    public SpeechItemSelectEvent() {
    }

    public SpeechItemSelectEvent(String speech) {
        mSpeech = speech;
    }

    public String getSpeech() {
        return mSpeech;
    }

    public void setSpeech(String speech) {
        mSpeech = speech;
    }
}
