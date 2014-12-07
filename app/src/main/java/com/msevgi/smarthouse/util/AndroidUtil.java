package com.msevgi.smarthouse.util;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

public final class AndroidUtil {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        IBinder mWindowToken = activity.getWindow().getDecorView().getRootView().getWindowToken();
        imm.hideSoftInputFromWindow(mWindowToken, 0);
    }
}
