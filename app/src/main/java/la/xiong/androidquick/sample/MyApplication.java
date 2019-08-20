package la.xiong.androidquick.sample;

import android.util.Log;

import com.androidwind.log.BuildConfig;
import com.androidwind.log.TinyLog;

import la.xiong.androidquick.module.network.retrofit.RetrofitManager;
import la.xiong.androidquick.sample.log.LogConfig;
import la.xiong.androidquick.sample.log.TinyLogProcessor;

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
        //log
        AndroidQuick
                // .configLog()
                .configLog(new TinyLogProcessor())
                .setEnable(BuildConfig.DEBUG)
                .setWritable(true)
                .setLevel(LogConfig.LOG_V);
        //make effective
        AndroidQuick.launch();

        //init retrofit url
        RetrofitManager.getInstance().initBaseUrl("http://gank.io/api/");
    }

    public static synchronized MyApplication getInstance() {
        return INSTANCE;
    }
}
