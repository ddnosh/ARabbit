package la.xiong.androidquick.sample;

import la.xiong.androidquick.module.network.retrofit.RetrofitManager;

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
        RetrofitManager.getInstance().initBaseUrl("http://gank.io/api/");
    }

    public static synchronized MyApplication getInstance() {
        return INSTANCE;
    }
}
