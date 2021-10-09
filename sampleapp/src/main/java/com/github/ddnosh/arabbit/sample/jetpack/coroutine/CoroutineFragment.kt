package com.github.ddnosh.arabbit.sample.jetpack.coroutine

import android.os.Bundle
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.AppConfig
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentCoroutineBinding
import com.github.ddnosh.arabbit.sample.module.network.rxjava.GankApis
import com.github.ddnosh.arabbit.sample.module.network.rxjava.RetrofitManager
import com.github.ddnosh.arabbit.util.ToastUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class CoroutineFragment : BaseFragment() {

    private val binding by binding<FragmentCoroutineBinding>()

    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    private val service: GankApis by lazy {
        RetrofitManager.getRetrofit(AppConfig.GANK_API_URL).create(GankApis::class.java)
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        binding.button.setOnClickListener {
            MainScope().launch(Dispatchers.Main) {
                val result = withContext(Dispatchers.IO) {
                    service.getHistoryDateAsync().await()
                }
                result.results?.size?.let { ToastUtil.showToast("" + it) }
            }
        }
    }
}
