package com.github.ddnosh.arabbit.sample.module.network.coroutine

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.ddnosh.arabbit.ext.parseState
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.module.network.coroutine.viewmodel.RequestProjectViewModel
import com.github.ddnosh.arabbit.util.LogUtil
import com.github.ddnosh.arabbit.util.ToastUtil
import kotlinx.android.synthetic.main.activity_network.*

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class NetworkFragment1 : BaseFragment() {

    private val requestProjectViewModel: RequestProjectViewModel by viewModels()

    override val contentViewLayoutID: Int = R.layout.activity_network

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

        access.setOnClickListener {
            requestProjectViewModel.getProjectTitleData()
        }
    }
}