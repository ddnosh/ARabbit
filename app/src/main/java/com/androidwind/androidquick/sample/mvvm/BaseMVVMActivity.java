package com.androidwind.androidquick.sample.mvvm;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.androidwind.androidquick.sample.base.BaseActivity;
import com.androidwind.androidquick.sample.util.ClassUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseMVVMActivity<T extends BaseViewModel> extends BaseActivity {

    private T mViewModel;

    public T getViewModel() {
        return mViewModel;
    }

    private void initViewModel() {
        Class<T> viewModelClass = ClassUtil.getViewModel(this);
        if (viewModelClass != null) {
            this.mViewModel = ViewModelProviders.of(this).get(viewModelClass);
        }
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        super.initViewsAndEvents(savedInstanceState);
        initViewModel();
        //注入RxLifecycle生命周期
        mViewModel.bindLifecycleProvider(lifecycleProvider);
    }
}
