package com.github.ddnosh.arabbit.sample.module.network.coroutine

import android.os.Bundle
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.ext.parseState
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.ActivityNetworkBinding
import com.github.ddnosh.arabbit.sample.module.network.coroutine.viewmodel.RequestProjectViewModel
import com.github.ddnosh.arabbit.util.LogUtil
import com.github.ddnosh.arabbit.util.ToastUtil

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class Network1Fragment : BaseFragment() {

    private val requestProjectViewModel by viewModel<RequestProjectViewModel>()

    private val binding by binding<ActivityNetworkBinding>()
    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        requestProjectViewModel.titleData.observe(viewLifecycleOwner, { data ->
            parseState(data, {
                it.map {
                    LogUtil.d(TAG, it.name)
                    ToastUtil.showToast(it.name)
                }
            }, {
            })
        })

        binding.access.setOnClickListener {
            requestProjectViewModel.getProjectTitleData()
        }
    }
}
