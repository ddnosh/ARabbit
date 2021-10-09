package com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata

import android.os.Bundle
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentBBinding

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class BFragment : BaseFragment() {
    private val binding by binding<FragmentBBinding>()

    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
    }
}
