package com.androidwind.androidquick.sample

import android.os.Bundle
import android.view.View
import com.androidwind.androidquick.sample.http.GankApis
import com.androidwind.androidquick.sample.http.RetrofitManager
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

    override fun getBundleExtras(extras: Bundle) {}
    override val contentViewLayoutID: Int = R.layout.activity_coroutine

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        super.initViewsAndEvents(savedInstanceState)
    }

    fun coroutine(view: View) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                service.getHistoryDateAsync().await()
            }
            result.results?.size?.let { ToastUtil.showToast("" + it) }
        }
    }
}