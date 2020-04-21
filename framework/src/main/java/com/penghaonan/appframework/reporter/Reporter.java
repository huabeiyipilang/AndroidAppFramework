package com.penghaonan.appframework.reporter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.penghaonan.appframework.AppDelegate;
import com.penghaonan.appframework.base.BaseFrameworkFragment;
import com.penghaonan.appframework.utils.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 *
 */
public class Reporter implements IReporter {

    private static Reporter sInstance;
    private final List<IReporter> mReporters = new LinkedList<>();

    private Reporter() {
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
                Logger.e("addReporter: reporter has been added!");
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
            if (appInfo != null && appInfo.metaData != null) {
                return appInfo.metaData.getString("apk_channel");
            }
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
    public void onFragmentResume(@NonNull BaseFrameworkFragment fragment) {
        synchronized (mReporters) {
            for (IReporter reporter : mReporters) {
                reporter.onFragmentResume(fragment);
            }
        }
    }

    @Override
    public void onFragmentPause(@NonNull BaseFrameworkFragment fragment) {
        synchronized (mReporters) {
            for (IReporter reporter : mReporters) {
                reporter.onFragmentPause(fragment);
            }
        }
    }

    @Override
    public void reportEvent(String eventId) {
        synchronized (mReporters) {
            Logger.i("onEvent:" + eventId);
            for (IReporter reporter : mReporters) {
                reporter.reportEvent(eventId);
            }
        }
    }

    @Override
    public void reportEvent(String eventId, String value) {
        synchronized (mReporters) {
            Logger.i("onEvent:" + eventId + ", value:" + value);
            for (IReporter reporter : mReporters) {
                reporter.reportEvent(eventId, value);
            }
        }
    }

    @Override
    public void reportEvent(String eventId, Map<String, String> values) {
        synchronized (mReporters) {
            Logger.i("onEvent:" + eventId + ", with values");
            for (IReporter reporter : mReporters) {
                reporter.reportEvent(eventId, values);
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
