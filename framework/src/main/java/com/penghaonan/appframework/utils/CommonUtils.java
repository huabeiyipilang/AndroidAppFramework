package com.penghaonan.appframework.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.penghaonan.appframework.AppDelegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CommonUtils {

    public final static int REQUEST_CODE_PERMISSION = 1000;

    /**
     * 检查需要的权限
     */
    public static void checkPermission(Activity activity, String permission) {
        String[] permissions = new String[]{permission};
        checkPermission(activity, permissions);
    }

    public static void checkPermission(Activity activity, String[] permissions) {
        if (permissions == null) {
            return;
        }

        checkPermission(activity, Arrays.asList(permissions));
    }

    public static void checkPermission(Activity activity, List<String> permissions) {
        if (activity == null || CollectionUtils.isEmpty(permissions)) {
            return;
        }
        permissions = new ArrayList<>(permissions);
        Iterator<String> iterator = permissions.iterator();
        while (iterator.hasNext()) {
            String permission = iterator.next();
            if (ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
                iterator.remove();
            }
        }
        if (!CollectionUtils.isEmpty(permissions)) {
            ActivityCompat.requestPermissions(activity, permissions.toArray(new String[0]), REQUEST_CODE_PERMISSION);
        }
    }

    public static boolean hasPermission(String permission) {
        return ActivityCompat.checkSelfPermission(AppDelegate.getApp(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 是否是主进程
     */
    public static boolean isMainProcess() {
        int pid = android.os.Process.myPid();
        String processAppName = getProcessName(pid);
        return processAppName != null && processAppName.equalsIgnoreCase(AppDelegate.getApp().getPackageName());
    }

    private static String getProcessName(int pID) {
        String processName;
        ActivityManager am = (ActivityManager) AppDelegate.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        for (Object aL : l) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (aL);
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }
}
