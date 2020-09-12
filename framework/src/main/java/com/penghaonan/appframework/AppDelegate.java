package com.penghaonan.appframework;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.penghaonan.appframework.reporter.Reporter;
import com.penghaonan.appframework.utils.Logger;
import com.penghaonan.appframework.utils.threadpool.IOThreadPool;
import com.penghaonan.appframework.utils.threadpool.ThreadPoolTask;

public class AppDelegate {

    private static AppDelegate sInstance;
    private static Handler mHandler;
    private static Application mAppContext;
    private static boolean mIsDebug;
    private static IOThreadPool threadPool;

    /**
     * 在应用Application.onCreate()方法中调用
     */
    public static AppDelegate init(Application application) {
        sInstance = new AppDelegate(application);
        Reporter.init();
        threadPool = new IOThreadPool();
        return sInstance;
    }

    public static void setDebug(boolean isDebug) {
        mIsDebug = isDebug;
        Logger.setEnable(isDebug);
    }

    public static boolean isDebug() {
        return mIsDebug;
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
        return mAppContext;
    }

    /**
     * 获取字符串
     */
    public static String getString(int resId) {
        return getApp().getString(resId);
    }

    public static String getString(int resId, Object... formatArgs) {
        return getApp().getString(resId, formatArgs);
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
        mHandler.post(runnable);
    }

    /**
     * 主线程延迟执行
     */
    public static void postDelayed(Runnable runnable, long delay) {
        mHandler.postDelayed(runnable, delay);
    }

    /**
     * 取消执行
     */
    public static void removeCallbacks(Runnable runnable) {
        mHandler.removeCallbacks(runnable);
    }

    public static void postAsync(ThreadPoolTask task) {
        threadPool.addTask(task);
    }

    public static void cancelAsync(ThreadPoolTask task) {
        threadPool.cancel(task);
    }

    /**
     * 打开activity
     */
    public static void startActivity(Class<? extends Activity> activityCls, Bundle bundle) {
        Intent intent = new Intent(getApp(), activityCls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public static void startActivity(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApp().startActivity(intent);
    }
}
