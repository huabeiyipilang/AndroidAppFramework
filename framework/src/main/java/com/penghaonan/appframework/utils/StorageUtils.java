package com.penghaonan.appframework.utils;

import android.os.Environment;

import java.io.File;

public class StorageUtils {

    //应用外部文件夹名
    private static String DIR_NAME;

    public static void setRootDir(String dir) {
        DIR_NAME = dir;
    }

    //应用外部目录路径
    public static File getExternalFolder() {
        if (DIR_NAME == null) {
            throw new NullPointerException("setRootDir before use!");
        }
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
