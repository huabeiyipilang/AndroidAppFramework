package com.penghaonan.appframework.utils;

import android.widget.Toast;

import com.penghaonan.appframework.AppDelegate;

public class ToastUtils {
    public static void showToast(final String msg) {
        AppDelegate.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AppDelegate.getApp(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showToast(final int msg) {
        AppDelegate.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AppDelegate.getApp(), msg, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
