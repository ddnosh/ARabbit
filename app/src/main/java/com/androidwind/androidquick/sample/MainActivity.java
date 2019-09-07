package com.androidwind.androidquick.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidwind.androidquick.module.retrofit.RetrofitManager;
import com.androidwind.androidquick.module.retrofit.exeception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;
import com.androidwind.androidquick.sample.GankApis;
import com.androidwind.androidquick.sample.GankRes;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.androidquick.util.ToastUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
