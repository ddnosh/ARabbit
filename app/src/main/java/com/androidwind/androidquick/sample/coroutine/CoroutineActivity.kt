package com.androidwind.androidquick.sample.coroutine

import android.os.Bundle
import android.view.View
import com.androidwind.androidquick.sample.AppConfig
import com.androidwind.androidquick.sample.base.BaseActivity
import com.androidwind.androidquick.sample.R
import com.androidwind.androidquick.sample.network.GankApis
import com.androidwind.androidquick.sample.network.RetrofitManager
import com.androidwind.androidquick.util.ToastUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class CoroutineActivity : BaseActivity() {
    private val service: GankApis by lazy {
        RetrofitManager.getRetrofit(AppConfig.GANK_API_URL).create(GankApis::class.java)
    }

    override val contentViewLayoutID: Int = R.layout.activity_coroutine
    override fun getBundleExtras(extras: Bundle) {}

    fun coroutine(view: View) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                service.getHistoryDateAsync().await()
            }
            result.results?.size?.let { ToastUtil.showToast("" + it) }
        }
    }
}