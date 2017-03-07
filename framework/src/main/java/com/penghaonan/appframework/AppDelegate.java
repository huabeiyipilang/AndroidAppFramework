package com.penghaonan.appframework;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.penghaonan.appframework.reporter.Reporter;
import com.penghaonan.appframework.utils.Logger;

public class AppDelegate {

    private static AppDelegate sInstance;
    private Handler mHandler;
    private Application mAppContext;

    /**
     * 在应用Application.onCreate()方法中调用
     */
    public static AppDelegate init(Application application) {
        sInstance = new AppDelegate(application);
        Reporter.init();
        return sInstance;
    }

    private AppDelegate(Application application) {
        mAppContext = application;
        Application.ActivityLifecycleCallbacks mActivityLifecycleCallback = new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Reporter.getInstance().onActivityResume(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Reporter.getInstance().onActivityPause(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        };
        mAppContext.registerActivityLifecycleCallbacks(mActivityLifecycleCallback);
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 获取全局Application
     */
    public static Application getApp() {
        return getInstance().mAppContext;
    }

    /**
     * 获取字符串
     */
    public static String getString(int resId) {
        return getApp().getString(resId);
    }

    /**
     * 获取单例实例
     */
    public static AppDelegate getInstance() {
        return sInstance;
    }

    /**
     * 主线程执行
     */
    public static void post(Runnable runnable) {
        getInstance().mHandler.post(runnable);
    }

    /**
     * 主线程延迟执行
     */
    public static void postDelayed(Runnable runnable, long delay) {
        getInstance().mHandler.postDelayed(runnable, delay);
    }

    /**
     * 取消执行
     */
    public static void removeCallbacks(Runnable runnable) {
        getInstance().mHandler.removeCallbacks(runnable);
    }

    /**
     * 打开activity
     */
    public static void startActivity(Class<? extends Activity> activityCls, Bundle bundle) {
        Intent intent = new Intent(getApp(), activityCls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        getApp().startActivity(intent);
    }
}
