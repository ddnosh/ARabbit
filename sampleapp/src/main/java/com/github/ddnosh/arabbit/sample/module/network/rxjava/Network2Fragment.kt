package com.github.ddnosh.arabbit.sample.module.network.rxjava

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.function.exception.AppException
import com.github.ddnosh.arabbit.function.exception.GlobalErrorProcessor
import com.github.ddnosh.arabbit.module.rxjava.BaseObserver
import com.github.ddnosh.arabbit.sample.AppConfig
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.ActivityNetworkBinding
import com.github.ddnosh.arabbit.sample.ext.composeWithLifecycleDestroy
import com.github.ddnosh.arabbit.util.LogUtil.e
import com.github.ddnosh.arabbit.util.LogUtil.i
import com.github.ddnosh.arabbit.util.ToastUtil
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle3.LifecycleProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class Network2Fragment : BaseFragment() {
    private val service: GankApis by lazy {
        RetrofitManager.getRetrofit(AppConfig.GANK_API_URL).create(GankApis::class.java)
    }

    private lateinit var lifecycleProvider: LifecycleProvider<Lifecycle.Event>

    private val binding by binding<ActivityNetworkBinding>()
    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        lifecycleProvider = AndroidLifecycle.createLifecycleProvider(this)
        binding.access.setOnClickListener {
            openNetwork()
        }
    }

    @SuppressLint("AutoDispose")
    fun openNetwork() { // 使用sdk自带的RetrofitManager, 返回Json字符串格式
        service.getHistoryDate()
            .composeWithLifecycleDestroy(lifecycleProvider)
            .subscribe(object : BaseObserver<GankRes<List<String>>>() {
                override fun onError(exception: AppException) {
                    e(TAG, "error:" + exception.message)
                    ToastUtil.showToast("Fail!")
                }

                override fun onSuccess(listGankRes: GankRes<List<String>>) {
                    i(TAG, listGankRes.results.toString())
                    ToastUtil.showToast("Success!")
                }
            })
        // 新建一个Retrofit, 返回普通string字符串格式
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create()) // 添加 string 转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加 RxJava 适配器
            .build()
        // 获取sdk
        service.getSdkVersion()
            .composeWithLifecycleDestroy(lifecycleProvider)
            .subscribe(object : BaseObserver<String?>() {
                override fun onError(exception: AppException) {}
                override fun onSuccess(result: String?) {}
            }
            )
        // error
        service.getError()
            .compose(activity?.let { GlobalErrorProcessor.processGlobalError(it) })
            .composeWithLifecycleDestroy(lifecycleProvider)
            .subscribe(object : BaseObserver<GankRes<List<String>>>() {
                override fun onError(exception: AppException) {}
                override fun onSuccess(result: GankRes<List<String>>) {}
            }
            )
    }
}
