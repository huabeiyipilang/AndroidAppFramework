package com.penghaonan.appframework.utils;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import androidx.core.content.FileProvider;

import com.penghaonan.appframework.AppDelegate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

public class FileUtils {
    private static final String TAG = "FileUtils";

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
    public static String getMimeType(File file) {
        String def = "file/*";
        String suffix = getSuffix(file);
        return getMimeTypeBySuffix(suffix);
    }

    public static String getMimeType(String fileName) {
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

    public interface CopyFileListener {
        /**
         * @return true abort
         */
        boolean onProgress(float progress);
    }

    public static boolean copyFile(File fileFrom, File fileTo, CopyFileListener listener) {
        try {
            int bytesum = 0;
            int byteread = 0;
            if (listener != null) {
                listener.onProgress(0f);
            }
            if (!fileTo.exists()) { //文件不存在时
                InputStream inStream = new FileInputStream(fileFrom.getAbsolutePath()); //读入原文件
                FileOutputStream fs = new FileOutputStream(fileTo.getAbsolutePath());
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    Logger.i(TAG, bytesum + " / " + fileFrom.length());
                    fs.write(buffer, 0, byteread);
                    if (listener != null) {
                        boolean abort = listener.onProgress(((float) bytesum) / fileFrom.length());
                        if (abort) {
                            break;
                        }
                    }
                }
                inStream.close();
                if (listener != null) {
                    listener.onProgress(1f);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listener != null) {
            listener.onProgress(1f);
        }
        return false;
    }

    public static void openFolder(File dir) {
        if (null == dir || !dir.exists()) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromFile(dir));
        AppDelegate.startActivity(intent);

//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setDataAndType(Uri.fromFile(dir), "file/*");
//        try {
//            AppDelegate.startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public static String getFileMD5(File file) {
        return getFileMD5(file, 16);
    }

    public static String getFileMD5(File file, int radix) {
        if (file == null || !file.isFile() || !file.exists()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(radix);
    }

    public static String getAuthority() {
        return AppDelegate.getApp().getPackageName() + ".fileprovider";
    }

    public static Uri uriFromFile(File file) {
        return FileProvider.getUriForFile(AppDelegate.getApp(), getAuthority(), file);
    }
}
