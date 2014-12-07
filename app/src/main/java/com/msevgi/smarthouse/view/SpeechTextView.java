package com.msevgi.smarthouse.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class SpeechTextView extends TextView {
    private String mSpeech;

    public SpeechTextView(Context context) {
        super(context);
    }

    public SpeechTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpeechTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getSpeech() {
        return mSpeech;
    }

    public void setSpeech(String speech) {
        mSpeech = speech;
    }
}
