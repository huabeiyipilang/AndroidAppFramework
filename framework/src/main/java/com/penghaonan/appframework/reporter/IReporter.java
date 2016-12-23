package com.penghaonan.appframework.reporter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

interface IReporter {

    void onActivityResume(@NonNull Activity activity);

    void onActivityPause(@NonNull Activity activity);

    void onFragmentResume(@NonNull Fragment fragment);

    void onFragmentPause(@NonNull Fragment fragment);

    void setChannel(String channel);
}
