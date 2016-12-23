package com.penghaonan.appframework.utils;

import android.os.Environment;

import java.io.File;

public class StorageUtils {

    //应用外部文件夹名
    private final static String DIR_NAME = "CLAppBackup";

    //应用外部目录路径
    public static File getExternalFolder() {
        File dir = Environment.getExternalStorageDirectory();
        dir = new File(dir, DIR_NAME);
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return null;
        }
        if (!dir.exists()) {
            boolean result = dir.mkdirs();
            if (!result) {
                return null;
            }
        }
        return dir;
    }
}
