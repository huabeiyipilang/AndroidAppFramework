package com.penghaonan.appframework.utils;

import android.util.Log;

public class Logger {
    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void i(String msg) {
        i(autoTag(), msg);
    }

    public static void e(Exception e) {
        e.printStackTrace();
    }

    private static String autoTag() {
        StackTraceElement[] elements = new Throwable().getStackTrace();
        String loggerClsName = Logger.class.getSimpleName();

        for (int i = 1; i < elements.length; i++) {
            String fileName = getFileName(elements[i]);
            if (!loggerClsName.equals(fileName)){
                return fileName;
            }
        }
        return "unknown";
    }

    private static String getFileName(StackTraceElement element) {
        String fileName = element.getFileName();
        return fileName.substring(0, fileName.indexOf("."));
    }
}
