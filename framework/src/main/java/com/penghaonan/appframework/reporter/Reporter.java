package com.penghaonan.appframework.reporter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.penghaonan.appframework.AppDelegate;
import com.penghaonan.appframework.utils.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Reporter implements IReporter {

    private static Reporter sInstance;
    private final List<IReporter> mReporters = new LinkedList<>();

    private Reporter() {
        mReporters.add(new FirebaseReporter());
    }

    public static void init() {
        sInstance = new Reporter();
    }

    public static Reporter getInstance() {
        return sInstance;
    }

    public void addReporter(IReporter reporter) {
        synchronized (mReporters) {
            if (reporter == null) {
                Logger.e("addReporter: reporter is null");
                return;
            }
            if (mReporters.contains(reporter)) {
                Logger.e("addReporter: reporter is null");
            } else {
                mReporters.add(reporter);
                setChannel(getChannel(AppDelegate.getApp()));
            }
        }
    }

    private String getChannel(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString("apk_channel");
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return "unknown";
    }

    public void onActivityResume(@NonNull Activity activity) {

        synchronized (mReporters) {
            for (IReporter reporter : mReporters) {
                reporter.onActivityResume(activity);
            }
        }
    }

    public void onActivityPause(@NonNull Activity activity) {
        synchronized (mReporters) {
            for (IReporter reporter : mReporters) {
                reporter.onActivityPause(activity);
            }
        }
    }

    @Override
    public void onFragmentResume(@NonNull Fragment fragment) {
        synchronized (mReporters) {
            for (IReporter reporter : mReporters) {
                reporter.onFragmentResume(fragment);
            }
        }
    }

    @Override
    public void onFragmentPause(@NonNull Fragment fragment) {
        synchronized (mReporters) {
            for (IReporter reporter : mReporters) {
                reporter.onFragmentPause(fragment);
            }
        }
    }

    @Override
    public void onEvent(String eventId) {
        synchronized (mReporters) {
            Logger.i("onEvent:" + eventId);
            for (IReporter reporter : mReporters) {
                reporter.onEvent(eventId);
            }
        }
    }

    @Override
    public void setChannel(String channel) {
        synchronized (mReporters) {
            if (TextUtils.isEmpty(channel)) {
                return;
            }
            for (IReporter reporter : mReporters) {
                reporter.setChannel(channel);
            }
        }
    }

}
