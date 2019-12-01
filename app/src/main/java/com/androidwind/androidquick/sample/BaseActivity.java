package com.androidwind.androidquick.sample;

import android.content.Intent;
import android.view.View;

import com.androidwind.androidquick.eventbus.EventCenter;
import com.androidwind.androidquick.ui.base.QuickActivity;
import com.androidwind.androidquick.util.LogUtil;

/**
 * 通用抽象方法的实现集合
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseActivity extends QuickActivity {

    protected static String TAG = "BaseActivity";

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
        return false;
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
