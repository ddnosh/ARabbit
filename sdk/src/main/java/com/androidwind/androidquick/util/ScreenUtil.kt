package com.androidwind.androidquick.util

import android.content.Context

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object ScreenUtil {

    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun dp2px(context: Context?, dipValue: Float): Int {
        if (context != null) {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }
        return dipValue.toInt()
    }

    fun getDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }
}
