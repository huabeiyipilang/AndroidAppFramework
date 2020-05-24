package com.penghaonan.appframework.base;

import androidx.appcompat.app.AppCompatActivity;

import com.penghaonan.appframework.reporter.Reporter;

public class BaseFrameworkActivity extends AppCompatActivity {

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
}
