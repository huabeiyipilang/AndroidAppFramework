package com.penghaonan.appframework.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class AFViewPager extends ViewPager {
    private boolean enableScroll = true;

    public AFViewPager(Context context) {
        super(context);
    }

    public AFViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 是否可以滚动
     */
    public void setEnableScroll(boolean enableScroll) {
        this.enableScroll = enableScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        if (enableScroll) {
            super.scrollTo(x, y);
        }
    }
}
