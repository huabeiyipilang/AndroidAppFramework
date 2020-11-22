package com.penghaonan.appframework.utils;

import android.text.TextUtils;
import android.util.Log;

public class Logger {
    private final static String DEF_TAG = Logger.class.getSimpleName();
    private static boolean enable;

    public static boolean isDebug() {
        return enable;
    }

    public static void setEnable(boolean enable) {
        Logger.enable = enable;
    }

    public static void i(String tag, String msg) {
        log(Log.INFO, tag, msg);
    }

    public static void e(String tag, String msg) {
        log(Log.ERROR, tag, msg);
    }

    public static void i(String msg) {
        i(autoTag(), msg);
    }

    public static void e(String msg) {
        e(autoTag(), msg);
    }

    public static void e(Exception e) {
        e(e.toString());
    }

    public static void log(int priority, String tag, String msg) {
        if (enable) {
            Log.println(priority, tag, msg);
        }
    }

    private static String autoTag() {
        if (!enable) {
            return DEF_TAG;
        }
        StackTraceElement[] elements = new Throwable().getStackTrace();
        String loggerClsName = Logger.class.getSimpleName();

        for (int i = 1; i < elements.length; i++) {
            String fileName = getFileName(elements[i]);
            if (!loggerClsName.equals(fileName)) {
                return fileName;
            }
        }
        return DEF_TAG;
    }

    private static String getFileName(StackTraceElement element) {
        String fileName = element.getFileName();
        if (TextUtils.isEmpty(fileName)) {
            return "";
        }
        return fileName.substring(0, fileName.indexOf("."));
    }
}
