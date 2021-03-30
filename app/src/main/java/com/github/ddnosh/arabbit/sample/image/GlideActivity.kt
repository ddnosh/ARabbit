package com.github.ddnosh.arabbit.sample.image

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.github.ddnosh.arabbit.module.glide.GlideManager
import com.github.ddnosh.arabbit.sample.base.BaseActivity
import com.github.ddnosh.arabbit.sample.R

class GlideActivity : BaseActivity() {
    override val contentViewLayoutID: Int = R.layout.activity_glide

    fun openImage(v: View?) {
        GlideManager
                .loadNet("https://hbimg.huabanimg.com/ca0077ed805df7a1566d68a74cae73c59994929b73f68-nkFwhw_fw658", findViewById<View>(R.id.imageView) as ImageView)
    }
}