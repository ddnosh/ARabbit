package com.github.ddnosh.arabbit.sample

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.ext.setDefaultAdapter
import com.github.ddnosh.arabbit.module.rxbus.RxBus
import com.github.ddnosh.arabbit.sample.base.BaseActivity
import com.github.ddnosh.arabbit.sample.databinding.ActivityMainBinding
import com.github.ddnosh.arabbit.sample.module.communication.rxbus.TestEvent
import com.github.ddnosh.arabbit.sample.util.TimeUtils
import com.github.ddnosh.arabbit.util.LogUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity() {

    private var titleList = arrayListOf("JetPack", "Module", "Function", "UI")

    private val binding by binding<ActivityMainBinding>()
    override fun attachViewBinding(): ViewBinding {
        return binding
    }
    override val contentViewLayoutID: Int = R.layout.activity_main

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        // RxBus
        RxBus.get().on(
            TestEvent::class.java, this, {
                LogUtil.d(TAG, "[RxBus] ${it.data}")
            }
        )

        val fragmentList = ArrayList<Fragment>()
        for (i in titleList.indices) {
            fragmentList.add(TabFragment.newInstance(i))
        }
        binding.viewPager2.run {
            offscreenPageLimit = fragmentList.size
            setDefaultAdapter(this@MainActivity, fragmentList)
            TabLayoutMediator(binding.tabLayout, binding.viewPager2, true, true) { tab: TabLayout.Tab, position: Int ->
                tab.text = titleList[position]
            }.attach()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (hasFocus) {
            val hotStartTime = TimeUtils.getTimeCalculate(TimeUtils.HOT_START)
            Log.d(TAG, "热启动时间:$hotStartTime")
            if (TimeUtils.sColdStartTime > 0 && hotStartTime > 0) {
                // 真正的冷启动时间 = Application启动时间 + 热启动时间
                val coldStartTime = TimeUtils.sColdStartTime + hotStartTime
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
