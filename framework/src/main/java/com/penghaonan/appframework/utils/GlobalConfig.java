package com.penghaonan.appframework.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.penghaonan.appframework.AppDelegate;

public class GlobalConfig {

    private static SharedPreferences sPref = AppDelegate.getApp().getSharedPreferences(AppDelegate.getApp().getPackageName(), Context.MODE_PRIVATE);

    private GlobalConfig() {
    }

    public static SharedPreferences getDefault() {
        return sPref;
    }

    public static void put(String key, boolean value) {
        sPref.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean def) {
        return sPref.getBoolean(key, def);
    }

    public static void put(String key, String value) {
        sPref.edit().putString(key, value).apply();
    }

    public static String getString(String key, String def) {
        return sPref.getString(key, def);
    }

    public static void put(String key, long value) {
        sPref.edit().putLong(key, value).apply();
    }

    public static long getLong(String key, long def) {
        return sPref.getLong(key, def);
    }
}
