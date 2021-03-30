package com.github.ddnosh.arabbit.sample.network

import com.github.ddnosh.arabbit.sample.AppConfig
import com.github.ddnosh.arabbit.sample.MyApplication
import com.github.ddnosh.arabbit.util.FileUtil
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object RetrofitManager {

    private val TAG = "RetrofitManager"
    private val retrofitMap = HashMap<String, Retrofit>()
    private fun createRetrofit(baseUrl: String, isJson: Boolean) {
        val timeOut = AppConfig.HTTP_TIME_OUT
        val cache = Cache(MyApplication.instance?.let { FileUtil.getHttpImageCacheDir(it) }!!,
            AppConfig.HTTP_MAX_CACHE_SIZE.toLong())
        // Log信息拦截器
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY //这里可以选择拦截级别
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(timeOut.toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()) //定义转化器,用Gson将服务器返回的Json格式解析成实体
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //关联RxJava
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
        if (isJson) {
            builder.addConverterFactory(GsonConverterFactory.create())
        } else {
            builder.addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
        }
        retrofitMap["$baseUrl-$isJson"] = builder.build()
    }

    private fun getRetrofit(baseUrl: String, isJson: Boolean): Retrofit {
        val key = "$baseUrl-$isJson"
        if (!retrofitMap.containsKey(key)) {
            createRetrofit(baseUrl, isJson)
        }
        return retrofitMap[key]!!
    }

    fun getRetrofit(baseUrl: String): Retrofit {
        return getRetrofit(baseUrl, true)
    }
}