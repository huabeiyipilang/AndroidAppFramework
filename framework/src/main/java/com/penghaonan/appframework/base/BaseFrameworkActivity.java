package com.penghaonan.appframework.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.penghaonan.appframework.reporter.Reporter;

public class BaseFrameworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
