package com.penghaonan.appframework.base;

import androidx.appcompat.app.AppCompatActivity;

import com.penghaonan.appframework.reporter.Reporter;
import com.penghaonan.appframework.utils.threadpool.ListViewThreadPool;

public class BaseFrameworkActivity extends AppCompatActivity {
    private ListViewThreadPool listViewThreadPool;

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

    public ListViewThreadPool getListViewThreadPool() {
        if (this.listViewThreadPool == null) {
            this.listViewThreadPool = new ListViewThreadPool();
        }
        return this.listViewThreadPool;
    }
}
