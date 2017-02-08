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

    public static int dip2Px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2Dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int getScreenWidthPixels(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        return 0;
    }

    /**
     * 设置StatusBar颜色
     */
    public static void initActivityStatusNavigationBarColor(Activity activity, int colorRes) {
        initActivityStatusNavigationBarColor(activity, colorRes, android.R.color.black);
    }

    /**
     * 设置StatusBar， NavigationBar颜色
     */
    public static void initActivityStatusNavigationBarColor(Activity activity, int sta_color_resId, int nav_color_resId) {
        if (activity != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (sta_color_resId != 0) {
                window.setStatusBarColor(AppDelegate.getApp().getResources().getColor(sta_color_resId));
            }
            if (nav_color_resId != 0) {
                window.setNavigationBarColor(AppDelegate.getApp().getResources().getColor(nav_color_resId));
            }
            ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, true);
            }
        }
    }
}
