package com.androidwind.androidquick.sample;

import android.os.Bundle;

import com.androidwind.androidquick.util.manager.QActivity;
import com.androidwind.androidquick.ui.receiver.NetStateReceiver;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.androidquick.util.ToastUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class JavaTestActivity extends BaseActivity {
    @Override
    protected int getContentViewLayoutID() {
        return 0;
    }

    @Override
    protected void getBundleExtras(@NotNull Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents(@Nullable Bundle savedInstanceState) {
        super.initViewsAndEvents(savedInstanceState);
        getBinding();
        LogUtil.i(TAG, "");
        ToastUtil.showToast("");
        QActivity.addActivity(this);
        NetStateReceiver.unRegisterNetworkStateReceiver(this);
        showLoadingDialog();
    }
}
