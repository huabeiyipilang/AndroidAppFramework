package com.penghaonan.appframework;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.penghaonan.appframework.reporter.Reporter;

public class AppDelegate {

    private static AppDelegate sInstance;
    private Handler mHandler;
    private Context mAppContext;

    public static AppDelegate init(Application application) {
        sInstance = new AppDelegate();
        sInstance.mAppContext = application;
        return sInstance;
    }

    public static Context getApp() {
        return getInstance().mAppContext;
    }

    public static AppDelegate getInstance() {
        return sInstance;
    }

    public void onCreate() {
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
