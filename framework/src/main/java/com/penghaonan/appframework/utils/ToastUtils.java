package com.penghaonan.appframework.utils;

import android.widget.Toast;

import com.penghaonan.appframework.AppDelegate;

public class ToastUtils {

    private static boolean ENABLE = true;

    public static void setEnable(boolean enable) {
        ENABLE = enable;
    }

    public static void showToast(final String msg) {
        if (!ENABLE) {
            return;
        }
        AppDelegate.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AppDelegate.getApp(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showToast(final int msg) {
        if (!ENABLE) {
            return;
        }
        AppDelegate.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AppDelegate.getApp(), msg, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
