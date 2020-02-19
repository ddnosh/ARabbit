package com.androidwind.androidquick.sample.mvvm;

import android.os.Bundle;
import android.view.View;

import com.androidwind.androidquick.sample.BaseActivity;
import com.androidwind.androidquick.sample.R;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class LiveDataActivity extends BaseActivity {

    private LiveDataViewModel mViewModel;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_livedata;
    }

    @Override
    protected void getBundleExtras(@NotNull Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(LiveDataViewModel.class);
        mViewModel.getLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

    }

    public void button1(View view) {
        mViewModel.test();
    }
}
