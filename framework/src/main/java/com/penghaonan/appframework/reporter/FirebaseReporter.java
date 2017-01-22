package com.penghaonan.appframework.reporter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.penghaonan.appframework.AppDelegate;

class FirebaseReporter implements IReporter {

    private FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(AppDelegate.getApp());

    @Override
    public void setChannel(String channel) {
        mFirebaseAnalytics.setUserProperty("channel", channel);
    }

    @Override
    public void onActivityResume(@NonNull Activity activity) {
        mFirebaseAnalytics.setCurrentScreen(activity, activity.getClass().getSimpleName(), null);
    }

    @Override
    public void onActivityPause(@NonNull Activity activity) {

    }

    @Override
    public void onFragmentResume(@NonNull Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            return;
        }
        mFirebaseAnalytics.setCurrentScreen(activity, fragment.getClass().getSimpleName(), null);
    }

    @Override
    public void onFragmentPause(@NonNull Fragment fragment) {

    }

    @Override
    public void onEvent(String eventId) {

    }
}
