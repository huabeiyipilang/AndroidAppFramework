package com.penghaonan.appframework.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import java.util.Iterator;
import java.util.List;

public class CommonUtils {
    public static void checkPermission(Activity activity, List<String> permissions) {
        if (activity == null || CollectionUtils.isEmpty(permissions)) {
            return;
        }
        Iterator<String> iterator = permissions.iterator();
        while (iterator.hasNext()) {
            String permission = iterator.next();
            if (ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
                iterator.remove();
            }
        }
        if (!CollectionUtils.isEmpty(permissions)) {
            ActivityCompat.requestPermissions(activity, (String[]) permissions.toArray(),1);
        }
    }
}
