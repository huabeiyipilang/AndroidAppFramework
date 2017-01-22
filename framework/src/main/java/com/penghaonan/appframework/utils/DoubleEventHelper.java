package com.penghaonan.appframework.utils;

import android.text.TextUtils;

import com.penghaonan.appframework.AppDelegate;

public class DoubleEventHelper {
    private long duration;
    private long lastTime;
    private String toastText;

    public DoubleEventHelper(long duration, String toast) {
        this.duration = duration;
        toastText = toast;
    }

    public DoubleEventHelper(long duration, int toastRes) {
        this.duration = duration;
        toastText = AppDelegate.getApp().getString(toastRes);
    }

    public boolean onEvent() {
        long now = System.currentTimeMillis();
        if (now - lastTime < duration) {
            lastTime = 0;
            return true;
        } else {
            if (!TextUtils.isEmpty(toastText)) {
                ToastUtils.showToast(toastText);
            }
            lastTime = now;
            return false;
        }
    }
}
