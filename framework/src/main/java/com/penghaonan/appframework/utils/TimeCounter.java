package com.penghaonan.appframework.utils;

import android.os.SystemClock;
import android.text.TextUtils;

public class TimeCounter {
    private long startTime;
    private long lastCountTime;

    public static TimeCounter start() {
        return new TimeCounter().startNow();
    }

    private TimeCounter startNow() {
        startTime = now();
        lastCountTime = startTime;
        return this;
    }

    public void count() {
        count(null);
    }

    public void count(String msg) {
        long nowTime = now();
        String log = "[" + (nowTime - startTime) + ", " + (nowTime - lastCountTime) + "]";
        if (!TextUtils.isEmpty(msg)) {
            log = msg + " " + log;
        }
        Logger.i(log);
        lastCountTime = nowTime;
    }

    private long now() {
        return SystemClock.elapsedRealtime();
    }
}
