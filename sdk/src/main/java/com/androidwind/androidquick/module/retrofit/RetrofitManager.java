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

    private static final String TAG = "RetrofitManager";

    private Retrofit retrofit;
    private Retrofit.Builder retrofitBuilder;
    private OkHttpClient okHttpClient;
    private OkHttpClient.Builder okHttpClientBuilder;

    private static String BASE_URL = "https://api.github.com";

    private static RetrofitManager mInstance;

    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitManager(BASE_URL);
                }
            }
        }
        return mInstance;
    }

    public RetrofitManager(String baseUrl) {
        BASE_URL = baseUrl;
        initOkHttp();
        LogUtil.i(TAG, "RetrofitManager ->" + BASE_URL);
    }

    public static void initBaseUrl(String url) {
        BASE_URL = url;
        LogUtil.i(TAG, "initBaseUrl ->" + BASE_URL);
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

    public <T> T createApi(Class<T> clazz) {
        if (retrofit == null) {
            synchronized (RetrofitManager.class) {
                if (retrofit == null) {
                    //Retrofit
                    retrofitBuilder = new Retrofit.Builder();
                    retrofitBuilder.baseUrl(BASE_URL)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())//定义转化器,用Gson将服务器返回的Json格式解析成实体
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());//关联RxJava
                    retrofit = retrofitBuilder.build();
                }
            }
        }
        LogUtil.i(TAG, "initRetrofit");
        return retrofit.create(clazz);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
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
