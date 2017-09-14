package com.penghaonan.appframework.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.penghaonan.appframework.reporter.Reporter;

abstract public class BaseFrameworkFragment extends Fragment {
    private View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutRes(), container, false);
        initViews(mRootView);
        initDatas();
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Reporter.getInstance().onFragmentResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Reporter.getInstance().onFragmentPause(this);
    }

    protected View findViewById(int id) {
        if (mRootView == null) {
            return null;
        }
        return mRootView.findViewById(id);
    }

    /**
     * 接收返回键按下事件
     *
     * @return boolean  false:back键事件未处理，向下传递。  true：消费掉该事件。
     */
    protected boolean onBackKeyDown() {
        return false;
    }

    abstract public int getLayoutRes();

    abstract public void initViews(View root);

    abstract public void initDatas();
}
