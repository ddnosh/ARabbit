package com.androidwind.androidquick.sample;

import androidx.multidex.MultiDexApplication;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MyApplication extends MultiDexApplication {

    private static MyApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        //get application
        if (INSTANCE == null) {
            INSTANCE = this;
        }
    }

    public static synchronized MyApplication getInstance() {
        return INSTANCE;
    }
}
