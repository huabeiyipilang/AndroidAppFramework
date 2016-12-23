package com.penghaonan.appframework.utils;

import android.util.Log;

public class Logger {
    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void e(Exception e) {
        e.printStackTrace();
    }
}
