package com.penghaonan.appframework.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.penghaonan.appframework.AppDelegate;

/**
 * Created by carl on 9/16/15.
 */
public class UiUtils {

    private static DisplayMetrics sDisplayMetrics;

    public static int dip2Px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2Dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getWindowWidth() {
        if (sDisplayMetrics == null) {
            DisplayMetrics metric = new DisplayMetrics();
            WindowManager wm = (WindowManager) AppDelegate.getApp().getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(metric);
            sDisplayMetrics = metric;
        }
        return sDisplayMetrics.widthPixels;
    }

    /**
     * 设置StatusBar颜色
     */
    public static void initActivityStatusNavigationBarColor(Activity activity, int color) {
        initActivityStatusNavigationBarColor(activity, color, 0);
    }

    /**
     * 设置StatusBar， NavigationBar颜色
     */
    public static void initActivityStatusNavigationBarColor(Activity activity, int sta_color, int nav_color) {
        if (activity != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (sta_color != 0) {
                window.setStatusBarColor(sta_color);
            }
            if (nav_color != 0) {
                window.setNavigationBarColor(nav_color);
            }
            ViewGroup mContentView = activity.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                mChildView.setFitsSystemWindows(true);
            }
        }
    }
}
