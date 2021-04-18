package com.github.ddnosh.arabbit.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.github.ddnosh.arabbit.ext.setDefaultAdapter
import com.github.ddnosh.arabbit.ext.toSafeObservable
import com.github.ddnosh.arabbit.module.rxbus.RxBus
import com.github.ddnosh.arabbit.sample.base.BaseActivity
import com.github.ddnosh.arabbit.sample.module.rxbus.TestEvent
import com.github.ddnosh.arabbit.sample.util.TimeUtils
import com.github.ddnosh.arabbit.util.LogUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val TAG = "MainActivity"

    var titleList = arrayListOf("JetPack", "Module", "Function", "UI")

    override val contentViewLayoutID: Int = R.layout.activity_main

    @SuppressLint("AutoDispose")
    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        //RxBus
        RxBus.getDefault().toSafeObservable(TestEvent::class.java, lifecycleProvider, this)
                .subscribe {
                    LogUtil.d(TAG, "[RxBus] ${it.data}")
                }

        val fragmentList = ArrayList<Fragment>()
        for (i in titleList.indices) {
            fragmentList.add(TabFragment.newInstance(i))
        }
        viewPager?.run {
            offscreenPageLimit = fragmentList.size
            setDefaultAdapter(this@MainActivity, fragmentList)
            TabLayoutMediator(tabLayout, viewPager) { tab: TabLayout.Tab, position: Int ->
                tab.text = titleList[position]
            }.attach()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (hasFocus) {
            val hotStartTime = TimeUtils.getTimeCalculate(TimeUtils.HOT_START);
            Log.d(TAG, "热启动时间:$hotStartTime")
            if (TimeUtils.sColdStartTime > 0 && hotStartTime > 0) {
                // 真正的冷启动时间 = Application启动时间 + 热启动时间
                val coldStartTime = TimeUtils.sColdStartTime + hotStartTime;
                Log.d(TAG, "application启动时间:" + TimeUtils.sColdStartTime)
                Log.d(TAG, "冷启动时间:$coldStartTime")
                // 过滤掉异常启动时间
                if (coldStartTime < 50000) {
                    // 上传冷启动时间coldStartTime
                }
            } else if (hotStartTime > 0) {
                // 过滤掉异常启动时间
                if (hotStartTime < 30000) {
                    // 上传热启动时间hotStartTime
                }
            }
        }
    }
}