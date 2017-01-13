package com.penghaonan.appframework.utils;

import com.penghaonan.appframework.AppDelegate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {
    public static String formatSize(long size) {
        DecimalFormat formater = new DecimalFormat("####");
        if (size < 1024) {
            return size + "bytes";
        } else if (size < 1024 * 1024) {
            float kbsize = size / 1024f;
            return formater.format(kbsize) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            float mbsize = size / 1024f / 1024f;
            return formater.format(mbsize) + "MB";
        } else if (size < 1024 * 1024 * 1024 * 1024) {
            formater = new DecimalFormat("####.00");
            float gbsize = size / 1024f / 1024f / 1024f;
            return formater.format(gbsize) + "GB";
        } else {
            return "size: error";
        }
    }

    public static String formatDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", AppDelegate.getApp().getResources().getConfiguration().locale);
        return sdf.format(new Date(time));
    }
}
