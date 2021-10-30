package com.github.ddnosh.arabbit.sample.ext

import android.os.Bundle
import androidx.navigation.NavController

var lastNavTime = 0L

fun NavController.navAction(resId: Int, bundle: Bundle? = null, interval: Long = 500) {
    val currentTime = System.currentTimeMillis()
    if (currentTime >= lastNavTime + interval) {
        lastNavTime = currentTime
        try {
            navigate(resId, bundle)
        } catch (ignore: Exception) {
            // 防止出现 当 fragment 中 action 的 duration设置为 0 时，连续点击两个不同的跳转会导致如下崩溃 #issue53
        }
    }
}
