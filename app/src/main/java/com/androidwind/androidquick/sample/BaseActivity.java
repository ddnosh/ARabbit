package com.androidwind.androidquick.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidwind.androidquick.module.asynchronize.eventbus.EventCenter;
import com.androidwind.androidquick.ui.base.QuickActivity;
import com.androidwind.androidquick.util.LogUtil;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import androidx.lifecycle.Lifecycle;

/**
 * 通用抽象方法的实现集合
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseActivity extends QuickActivity {

    protected static String TAG = "BaseActivity";
    protected LifecycleProvider<Lifecycle.Event> lifecycleProvider;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        lifecycleProvider = AndroidLifecycle.createLifecycleProvider(this);
    }

    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    protected View setDefaultVaryViewRoot() {
        return null;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        LogUtil.i(TAG, eventCenter.getEventCode() + "");
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.LEFT;
    }

    @Override
    protected boolean isLoadDefaultTitleBar() {
        return true;
    }

    @Override
    protected Intent getGoIntent(Class<?> clazz) {
        if (BaseFragment.class.isAssignableFrom(clazz)) {
            Intent intent = new Intent(this, FrameActivity.class);
            intent.putExtra("fragmentName", clazz.getName());
            return intent;
        } else {
            return super.getGoIntent(clazz);
        }
    }

}
