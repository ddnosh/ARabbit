package com.androidwind.androidquick.module.retrofit;

import com.androidwind.androidquick.BuildConfig;
import com.androidwind.androidquick.util.LogUtil;
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
    private Retrofit.Builder retrofitBuilder;
    private OkHttpClient okHttpClient;
    private OkHttpClient.Builder okHttpClientBuilder;

    private String BASE_URL;

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
        okHttpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别
            //设置 Debug Log 模式
            okHttpClientBuilder.addInterceptor(loggingInterceptor);
            //配置SSL证书检测
            okHttpClientBuilder.sslSocketFactory(SSLSocketClient.getNoSSLSocketFactory());
            okHttpClientBuilder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        }
        //错误重连
        okHttpClientBuilder.retryOnConnectionFailure(true);
        okHttpClient = okHttpClientBuilder.build();
        LogUtil.i(TAG, "initOkHttp:getNoSSLSocketFactory");
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
                    retrofitBuilder = new Retrofit.Builder();
                    retrofitBuilder.baseUrl(BASE_URL)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())//定义转化器,用Gson将服务器返回的Json格式解析成实体
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());//关联Rxjava
                    singleton = retrofitBuilder.build();
                }
            }
        }
        return singleton.create(clazz);
    }

    /*
        初始化url
     */
    public void initBaseUrl(String url) {
        BASE_URL = url;
        LogUtil.i(TAG, " base_url ->" + BASE_URL);
    }

    public Retrofit getSingleton() {
        return singleton;
    }

    public void setSingleton(Retrofit singleton) {
        this.singleton = singleton;
    }

    public Retrofit.Builder getRetrofitBuilder() {
        return retrofitBuilder;
    }

    public void setRetrofitBuilder(Retrofit.Builder retrofitBuilder) {
        this.retrofitBuilder = retrofitBuilder;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public OkHttpClient.Builder getOkHttpClientBuilder() {
        return okHttpClientBuilder;
    }

    public void setOkHttpClientBuilder(OkHttpClient.Builder okHttpClientBuilder) {
        this.okHttpClientBuilder = okHttpClientBuilder;
    }
}
