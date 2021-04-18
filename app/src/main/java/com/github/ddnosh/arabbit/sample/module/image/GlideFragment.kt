package com.github.ddnosh.arabbit.sample.module.image

import android.os.Bundle
import com.github.ddnosh.arabbit.module.glide.GlideManager
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_glide.*

class GlideFragment : BaseFragment() {
    override val contentViewLayoutID: Int = R.layout.fragment_glide

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        glide.setOnClickListener {
            GlideManager
                    .loadNet("https://hbimg.huabanimg.com/ca0077ed805df7a1566d68a74cae73c59994929b73f68-nkFwhw_fw658", imageView)
        }
    }
}