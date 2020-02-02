package com.androidwind.androidquick.sample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.androidwind.androidquick.util.ToastUtil
import com.androidwind.androidquick.util.ToastUtil.register
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val TAG = "MainActivity"
    override fun getBundleExtras(extras: Bundle) {}
    override val contentViewLayoutID: Int = R.layout.activity_main

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        register(applicationContext)
        toolbar.title = "首页"
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"))
//        binding!!.hasPendingBindings()
        mLayoutStatusView = multiple_status_view
        mLayoutStatusView?.setOnClickListener {
            ToastUtil.showToast("retried.")
        }
    }

    fun openActivity(v: View) {
        val clickTime = v.getClickTime
        println(clickTime)
        readyGo(DemoActivity::class.java)
    }

    fun openFragment(v: View) {
        readyGo(DemoFragment::class.java)
    }

    fun statusView(view: View) {
        mLayoutStatusView?.showError()
    }
}