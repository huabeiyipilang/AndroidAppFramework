package com.penghaonan.appframework;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.penghaonan.appframework.reporter.Reporter;

public class AppDelegate {

    private static AppDelegate sApp;
    private Handler mHandler;
    private Context mAppContext;

    public static Context getApp() {
        return getInstance().mAppContext;
    }

    public static AppDelegate getInstance() {
        return sApp;
    }

    public void onCreate(Application application) {
        sApp = this;
        mAppContext = application;
        mHandler = new Handler(Looper.getMainLooper());
        Reporter.init();
    }

    public static void post(Runnable runnable) {
        getInstance().mHandler.post(runnable);
    }

    public static void postDelayed(Runnable runnable, long delay) {
        getInstance().mHandler.postDelayed(runnable, delay);
    }

    public static void removeCallbacks(Runnable runnable) {
        getInstance().mHandler.removeCallbacks(runnable);
    }
}
