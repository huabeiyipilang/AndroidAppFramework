package com.penghaonan.appframework.utils;

import android.webkit.MimeTypeMap;

import java.io.File;
import java.util.Locale;

public class FileUtils {

    /**
     * 获取文件后缀
     */
    public static String getSuffix(File file) {
        if (file == null || !file.exists() || file.isDirectory()) {
            return null;
        }
        String fileName = file.getName();
        return getSuffix(fileName);
    }

    /**
     * 获取文件后缀
     */
    public static String getSuffix(String fileName) {
        if (fileName.equals("") || fileName.endsWith(".")) {
            return null;
        }
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            return fileName.substring(index + 1).toLowerCase(Locale.US);
        } else {
            return null;
        }
    }

    /**
     * 获取文件MimeType
     */
    public static String getMimeType(File file){
        String def = "file/*";
        String suffix = getSuffix(file);
        return getMimeTypeBySuffix(suffix);
    }

    public static String getMimeType(String fileName){
        String suffix = getSuffix(fileName);
        return getMimeTypeBySuffix(suffix);
    }

    public static String getMimeTypeBySuffix(String suffix) {
        String def = "file/*";
        if (suffix == null) {
            return def;
        }
        String type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(suffix);
        if (type != null && !type.isEmpty()) {
            return type;
        }
        return def;
    }
}
