package com.github.ddnosh.arabbit.sample.network

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.github.ddnosh.arabbit.module.exception.ApiException
import com.github.ddnosh.arabbit.module.rxjava.BaseObserver
import com.github.ddnosh.arabbit.sample.AppConfig
import com.github.ddnosh.arabbit.sample.base.BaseActivity
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.composeWithLifecycleDestroy
import com.github.ddnosh.arabbit.sample.error.GlobalErrorProcessor
import com.github.ddnosh.arabbit.util.LogUtil.e
import com.github.ddnosh.arabbit.util.LogUtil.i
import com.github.ddnosh.arabbit.util.ToastUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class NetworkActivity : BaseActivity() {
    private val service: GankApis by lazy {
        RetrofitManager.getRetrofit(AppConfig.GANK_API_URL).create(GankApis::class.java)
    }

    override val contentViewLayoutID: Int = R.layout.activity_network

    @SuppressLint("AutoDispose")
    fun openNetwork(v: View?) { //使用sdk自带的RetrofitManager, 返回Json字符串格式
        service.getHistoryDate()
                .composeWithLifecycleDestroy(lifecycleProvider)
                .subscribe(object : BaseObserver<GankRes<List<String>>>() {
                    override fun onError(exception: ApiException) {
                        e(TAG, "error:" + exception.message)
                        ToastUtil.showToast("Fail!")
                    }

                    override fun onSuccess(listGankRes: GankRes<List<String>>) {
                        i(TAG, listGankRes?.results.toString())
                        ToastUtil.showToast("Success!")
                    }
                })
        //新建一个Retrofit, 返回普通string字符串格式
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create()) //添加 string 转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //添加 RxJava 适配器
                .build()
        //获取sdk
        service.getSdkVersion()
                .composeWithLifecycleDestroy(lifecycleProvider)
                .subscribe(object : BaseObserver<String?>() {
                    override fun onError(exception: ApiException) {}
                    override fun onSuccess(result: String?) {}
                }
                )
        //error
        service.getError()
                .compose(GlobalErrorProcessor.processGlobalError(this))
                .composeWithLifecycleDestroy(lifecycleProvider)
                .subscribe(object : BaseObserver<GankRes<List<String>>>() {
                    override fun onError(exception: ApiException) {}
                    override fun onSuccess(result: GankRes<List<String>>) {}
                }
                )
    }
}