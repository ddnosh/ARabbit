package com.androidwind.androidquick.sample;

import android.os.Bundle;
import android.view.View;

import com.androidwind.androidquick.util.ToastUtil;

public class MainActivity extends BaseActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        ToastUtil.register(getApplicationContext());
    }

    public void OpenActivity(View v) {
        readyGo(DemoActivity.class);
    }

    public void OpenFragment(View v) {
        readyGo(DemoFragment.class);
    }
}
