package com.penghaonan.appframework.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.viewpager.widget.ViewPager;

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
