package com.penghaonan.appframework.reporter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * 客户端自定义上报，需实现此接口
 * Reporter.getInstance().addReporter()
 */
public interface IReporter {
    /**
     * 框架调用
     */
    void setChannel(String channel);

    /**
     * 框架调用
     */
    void onActivityResume(@NonNull Activity activity);

    /**
     * 框架调用
     */
    void onActivityPause(@NonNull Activity activity);

    /**
     * 框架调用（需继承BaseFrameworkFragment）
     */
    void onFragmentResume(@NonNull Fragment fragment);

    /**
     * 框架调用（需继承BaseFrameworkFragment）
     */
    void onFragmentPause(@NonNull Fragment fragment);
}
