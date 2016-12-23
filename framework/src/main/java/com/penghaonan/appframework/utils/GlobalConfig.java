package com.penghaonan.appframework.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.penghaonan.appframework.AppDelegate;

public class GlobalConfig {
    private static GlobalConfig sInstance;

    private Context mContext;
    private SharedPreferences mPref;

    private GlobalConfig(Context context) {
        mContext = context;
        mPref = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE);
    }

    public static GlobalConfig getInstance() {
        if (sInstance == null) {
            sInstance = new GlobalConfig(AppDelegate.getApp());
        }
        return sInstance;
    }

    public SharedPreferences getDefault() {
        return mPref;
    }
}
