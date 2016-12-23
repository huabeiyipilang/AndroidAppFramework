package com.penghaonan.appframework.utils;

import android.widget.Toast;

import com.penghaonan.appbackup.framework.App;

public class ToastUtils {
    public static void showToast(String msg) {
        Toast.makeText(App.getApp(), msg, Toast.LENGTH_SHORT).show();
    }
    public static void showToast(int msg) {
        Toast.makeText(App.getApp(), msg, Toast.LENGTH_SHORT).show();
    }
}
