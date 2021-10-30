package com.github.ddnosh.arabbit.sample.module.communication.rxbus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.ext.toast
import com.github.ddnosh.arabbit.module.rxbus.RxBus
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentRxbusBinding

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class RxBusFragment : BaseFragment() {

    private val binding by binding<FragmentRxbusBinding>()
    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    @SuppressLint("AutoDispose")
    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        RxBus.get().on(TestEvent::class.java, this, {
            mContext.toast(it.data)
        })

        binding.post.setOnClickListener {
            RxBus.get().fire(TestEvent("this's an event"))
        }
    }
}
