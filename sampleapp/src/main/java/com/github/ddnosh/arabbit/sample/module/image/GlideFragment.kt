package com.github.ddnosh.arabbit.sample.module.image

import android.os.Bundle
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.module.glide.GlideManager
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentGlideBinding

class GlideFragment : BaseFragment() {

    private val binding by binding<FragmentGlideBinding>()
    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        binding.glide.setOnClickListener {
            GlideManager
                .loadNet("https://hbimg.huabanimg.com/ca0077ed805df7a1566d68a74cae73c59994929b73f68-nkFwhw_fw658", binding.imageView)
        }
    }
}
