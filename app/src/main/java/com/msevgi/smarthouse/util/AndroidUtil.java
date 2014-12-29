package com.msevgi.smarthouse.util;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

public final class AndroidUtil {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        IBinder windowToken = activity.getWindow().getDecorView().getRootView().getWindowToken();
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
    }
}
