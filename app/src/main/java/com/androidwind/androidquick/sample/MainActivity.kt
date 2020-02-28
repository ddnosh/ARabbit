package com.androidwind.androidquick.sample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.androidwind.androidquick.sample.mvvm.LiveDataActivity
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
        multiple_status_view.setOnClickListener {
            ToastUtil.showToast("retried.")
        }
//        showLoadingDialog()
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
        multiple_status_view.showError()
    }

    fun coroutine(view: View) {
        readyGo(CoroutineActivity::class.java)
    }

    fun dialog(view: View) {
        readyGo(DialogActivity::class.java)
    }

    fun livedata(view: View) {
        readyGo(LiveDataActivity::class.java)
    }
}