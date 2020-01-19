package com.androidwind.androidquick.common;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class SafeHandler<T> extends Handler {

    private final WeakReference<T> mPage;

    public SafeHandler(T page) {
        mPage = new WeakReference<>(page);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        T page = mPage.get();
        if (page != null) {
            disposeMessage(page, msg);
        }
    }

    public abstract void disposeMessage(T t, Message msg);
}
