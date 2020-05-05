package com.androidwind.androidquick.sample.image

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.androidwind.androidquick.module.glide.GlideManager
import com.androidwind.androidquick.sample.base.BaseActivity
import com.androidwind.androidquick.sample.R

class GlideActivity : BaseActivity() {
    override val contentViewLayoutID: Int = R.layout.activity_glide

    override fun getBundleExtras(extras: Bundle) {

    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        super.initViewsAndEvents(savedInstanceState)

    }

    fun openImage(v: View?) {
        GlideManager
                .loadNet("https://hbimg.huabanimg.com/ca0077ed805df7a1566d68a74cae73c59994929b73f68-nkFwhw_fw658", findViewById<View>(R.id.imageView) as ImageView)
    }
}