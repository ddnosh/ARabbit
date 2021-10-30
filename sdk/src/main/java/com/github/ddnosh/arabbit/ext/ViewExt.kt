package com.github.ddnosh.arabbit.ext

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.github.ddnosh.arabbit.R

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

private var <T : View>T.lastTimeTrigger: Long
    get() = if (getTag(R.id.lastTimeTriggerKey) != null) getTag(R.id.lastTimeTriggerKey) as Long else 0
    set(value) {
        setTag(R.id.lastTimeTriggerKey, value)
    }

private var <T : View> T.delayTrigger: Long
    get() = if (getTag(R.id.delayTriggerKey) != null) getTag(R.id.delayTriggerKey) as Long else -1
    set(value) {
        setTag(R.id.delayTriggerKey, value)
    }

private fun <T : View> T.clickable(): Boolean {
    var clickable = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - lastTimeTrigger >= delayTrigger) {
        clickable = true
    }
    lastTimeTrigger = currentClickTime
    return clickable
}

fun <T : View> T.clickWithDelay(delay: Long = 500, block: (T) -> Unit) {
    delayTrigger = delay
    setOnClickListener {
        if (clickable()) {
            block(this)
        }
    }
}
