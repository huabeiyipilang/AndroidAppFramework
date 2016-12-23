package com.penghaonan.appframework.utils;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;

public class CommonUtils {
    public static void checkPermission(Activity activity) {
        if (activity == null) {
            return;
        }
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
    }
}
