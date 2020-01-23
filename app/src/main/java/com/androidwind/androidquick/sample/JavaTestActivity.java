package com.androidwind.androidquick.sample;

import android.os.Bundle;

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
    }
}
