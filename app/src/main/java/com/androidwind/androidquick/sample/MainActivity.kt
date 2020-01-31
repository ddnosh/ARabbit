package com.androidwind.androidquick.sample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.androidwind.androidquick.util.ToastUtil.register

class MainActivity : BaseActivity() {
    private val TAG = "MainActivity"
    override fun getBundleExtras(extras: Bundle) {}
    override val contentViewLayoutID: Int = R.layout.activity_main

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        register(applicationContext)
        toolbar.title = "首页"
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"))
//        binding!!.hasPendingBindings()
    }

    fun openActivity(v: View?) {
        readyGo(DemoActivity::class.java)
    }

    fun openFragment(v: View?) {
        readyGo(DemoFragment::class.java)
    }
}