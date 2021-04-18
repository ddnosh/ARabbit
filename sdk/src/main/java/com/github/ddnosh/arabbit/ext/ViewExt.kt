package com.github.ddnosh.arabbit.ext

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

var <T : View> T.getClickTime: Long?
    get() {
        val tag = getTag(100) as Long?
        return tag?.run {
            this
        } ?: run {
            0L
        }
    }
    set(value) = setTag(100, System.currentTimeMillis())

fun ViewPager2.setDefaultAdapter(
        fragmentActivity: FragmentActivity,
        fragmentList: MutableList<Fragment>,
        selectAction: ((position: Int) -> Unit)? = null
): FragmentStateAdapter {
    val defaultAdapter = object : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount() = fragmentList.size

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }
    adapter = defaultAdapter
    selectAction?.let {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                it.invoke(position)
            }
        })
    }

    return defaultAdapter
}