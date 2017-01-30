package com.penghaonan.appframework.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.penghaonan.appframework.AppDelegate;

import java.util.Iterator;
import java.util.List;

public class CommonUtils {

    /**
     * 检查需要的权限
     */
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
            ActivityCompat.requestPermissions(activity, permissions.toArray(new String[0]), 1);
        }
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
