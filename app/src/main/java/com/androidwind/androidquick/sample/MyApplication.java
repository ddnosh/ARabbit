package com.androidwind.androidquick.sample;

import com.androidwind.androidquick.module.retrofit.RetrofitManager;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MyApplication extends android.support.multidex.MultiDexApplication {

    private static MyApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        //get application
        if (INSTANCE == null) {
            INSTANCE = this;
        }

        //init retrofit url
        RetrofitManager.initBaseUrl("http://gank.io/api/");
    }

    public static synchronized MyApplication getInstance() {
        return INSTANCE;
    }
}
