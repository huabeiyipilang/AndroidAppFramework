package com.penghaonan.appframework.reporter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.penghaonan.appframework.AppDelegate;

import java.util.LinkedList;
import java.util.List;

public class Reporter implements IReporter {

    private static Reporter sInstance;
    private List<IReporter> mReporters = new LinkedList<>();

    private Reporter() {
        mReporters.add(new FirebaseReporter());
        setChannel(getChannel(AppDelegate.getApp()));
    }

    public static void init() {
        sInstance = new Reporter();
    }

    public static Reporter getInstance() {
        return sInstance;
    }

    private String getChannel(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString("apk_channel");
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return "";
    }

    public void onActivityResume(@NonNull Activity activity) {
        for (IReporter reporter : mReporters) {
            reporter.onActivityResume(activity);
        }
    }

    public void onActivityPause(@NonNull Activity activity) {
        for (IReporter reporter : mReporters) {
            reporter.onActivityPause(activity);
        }
    }

    @Override
    public void onFragmentResume(@NonNull Fragment fragment) {
        for (IReporter reporter : mReporters) {
            reporter.onFragmentResume(fragment);
        }
    }

    @Override
    public void onFragmentPause(@NonNull Fragment fragment) {
        for (IReporter reporter : mReporters) {
            reporter.onFragmentPause(fragment);
        }
    }

    @Override
    public void setChannel(String channel) {
        if (TextUtils.isEmpty(channel)) {
            return;
        }
        for (IReporter reporter : mReporters) {
            reporter.setChannel(channel);
        }
    }

}
