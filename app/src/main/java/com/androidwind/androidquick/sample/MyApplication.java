package com.androidwind.androidquick.sample;

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
    }

    public static synchronized MyApplication getInstance() {
        return INSTANCE;
    }
}
