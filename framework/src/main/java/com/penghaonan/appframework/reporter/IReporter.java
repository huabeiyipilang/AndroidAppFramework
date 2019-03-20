package com.penghaonan.appframework.reporter;

import android.app.Activity;

import com.penghaonan.appframework.base.BaseFrameworkFragment;

import java.util.Map;

import androidx.annotation.NonNull;

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
    void onFragmentResume(@NonNull BaseFrameworkFragment fragment);

    /**
     * 框架调用（需继承BaseFrameworkFragment）
     */
    void onFragmentPause(@NonNull BaseFrameworkFragment fragment);

    /**
     * 计数事件
     */
    void onEvent(String eventId);

    /**
     * 计数事件
     */
    void onEvent(String eventId, String value);

    /**
     * 计数事件
     */
    void onEvent(String eventId, Map<String, String> values);
}
