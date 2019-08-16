package la.xiong.androidquick.module.network.retrofit;

import android.content.Context;

import la.xiong.androidquick.BuildConfig;
import la.xiong.androidquick.tool.LogUtil;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class RetrofitManager {

    private final String TAG = "RetrofitManager";
    private Retrofit singleton;
    private OkHttpClient okHttpClient = null;
    private String BASE_URL = "http://127.0.0.1";

    public static RetrofitManager mInstance;

    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitManager();
                }
            }
        }
        return mInstance;
    }

    public RetrofitManager() {
        initOkHttp();
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
            //配置SSL证书检测
            builder.sslSocketFactory(SSLSocketClient.getNoSSLSocketFactory());
            builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        }
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
        LogUtil.i(TAG, "initOkHttp:getNoSSLSocketFactory");
    }

    public void initBaseUrl(String url) {
        BASE_URL = url;
        LogUtil.i(TAG, " base_url ->" + BASE_URL);
    }

    /**
     * @param clazz   interface
     * @param <T>     interface实例化
     * @return
     */
    public <T> T createApi(Class<T> clazz) {
        if (singleton == null) {
            synchronized (RetrofitManager.class) {
                if (singleton == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(BASE_URL)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())//定义转化器,用Gson将服务器返回的Json格式解析成实体
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());//关联Rxjava
                    singleton = builder.build();
                }
            }
        }
        return singleton.create(clazz);
    }
}
