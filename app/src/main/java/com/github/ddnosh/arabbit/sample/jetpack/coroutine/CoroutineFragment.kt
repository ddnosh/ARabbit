package com.github.ddnosh.arabbit.sample.jetpack.coroutine

import android.os.Bundle
import com.github.ddnosh.arabbit.sample.AppConfig
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.module.network.rxjava.GankApis
import com.github.ddnosh.arabbit.sample.module.network.rxjava.RetrofitManager
import com.github.ddnosh.arabbit.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_coroutine.*
import kotlinx.coroutines.*

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class CoroutineFragment : BaseFragment() {
    private val service: GankApis by lazy {
        RetrofitManager.getRetrofit(AppConfig.GANK_API_URL).create(GankApis::class.java)
    }

    override val contentViewLayoutID: Int = R.layout.fragment_coroutine
    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        button.setOnClickListener {
            MainScope().launch(Dispatchers.Main) {
                val result = withContext(Dispatchers.IO) {
                    service.getHistoryDateAsync().await()
                }
                result.results?.size?.let { ToastUtil.showToast("" + it) }
            }
        }
    }
}