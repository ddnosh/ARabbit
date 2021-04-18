package com.github.ddnosh.arabbit.sample.module.rxbus

import android.annotation.SuppressLint
import android.os.Bundle
import com.github.ddnosh.arabbit.ext.toSafeObservable
import com.github.ddnosh.arabbit.ext.toast
import com.github.ddnosh.arabbit.module.rxbus.RxBus
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.util.LogUtil
import kotlinx.android.synthetic.main.fragment_rxbus.*
import kotlinx.android.synthetic.main.fragment_tab.*

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class RxBusFragment : BaseFragment() {

    override val contentViewLayoutID: Int = R.layout.fragment_rxbus

    @SuppressLint("AutoDispose")
    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        RxBus.getDefault().toSafeObservable(TestEvent::class.java, lifecycleProvider, this)
                .subscribe {
                    mContext.toast(it.data)
                }

        post.setOnClickListener {
            RxBus.getDefault().post(TestEvent("this's an event"))
        }
    }
}