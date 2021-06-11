package com.penghaonan.appframework.base;

import androidx.appcompat.app.AppCompatActivity;

import com.penghaonan.appframework.reporter.Reporter;
import com.penghaonan.appframework.utils.threadpool.IThreadPoolHolder;
import com.penghaonan.appframework.utils.threadpool.UIThreadPool;

public class BaseFrameworkActivity extends AppCompatActivity implements IThreadPoolHolder {
    private UIThreadPool uiThreadPool;

    @Override
    protected void onResume() {
        super.onResume();
        Reporter.getInstance().onActivityResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Reporter.getInstance().onActivityPause(this);
    }

    @Override
    public UIThreadPool getUIThreadPool() {
        if (this.uiThreadPool == null) {
            this.uiThreadPool = new UIThreadPool();
        }
        return this.uiThreadPool;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (uiThreadPool != null) {
            uiThreadPool.stop(true);
        }
    }
}
