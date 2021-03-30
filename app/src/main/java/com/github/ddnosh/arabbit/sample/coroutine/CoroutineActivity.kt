package com.github.ddnosh.arabbit.sample.coroutine

import android.os.Bundle
import com.github.ddnosh.arabbit.sample.AppConfig
import com.github.ddnosh.arabbit.sample.base.BaseActivity
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.network.GankApis
import com.github.ddnosh.arabbit.sample.network.RetrofitManager
import com.github.ddnosh.arabbit.util.ToastUtil
import kotlinx.coroutines.*

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class CoroutineActivity : BaseActivity() {
    private val service: GankApis by lazy {
        RetrofitManager.getRetrofit(AppConfig.GANK_API_URL).create(GankApis::class.java)
    }

    override val contentViewLayoutID: Int = R.layout.activity_coroutine

    fun coroutine() {
        MainScope().launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                service.getHistoryDateAsync().await()
            }
            result.results?.size?.let { ToastUtil.showToast("" + it) }
        }
    }
}