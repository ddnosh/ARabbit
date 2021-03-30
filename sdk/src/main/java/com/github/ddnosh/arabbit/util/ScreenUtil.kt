package com.github.ddnosh.arabbit.util

import android.content.Context

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object ScreenUtil {

    @JvmStatic
    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    @JvmStatic
    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    @JvmStatic
    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    @JvmStatic
    fun dp2px(context: Context?, dipValue: Float): Int {
        if (context != null) {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }
        return dipValue.toInt()
    }

    @JvmStatic
    fun getDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }
}
