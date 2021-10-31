package com.github.ddnosh.arabbit.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager.NameNotFoundException
import android.graphics.Color
import android.preference.PreferenceManager
import androidx.core.content.ContextCompat
import com.github.ddnosh.arabbit.R

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object AppUtil {

    private const val TAG = "AppUtil"

    /**
     * get App versionCode
     * @param context
     * @return
     */
    @JvmStatic
    fun getVersionCode(context: Context): String {
        val packageManager = context.packageManager
        val packageInfo: PackageInfo
        var versionCode = ""
        try {
            packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            versionCode = packageInfo.versionCode.toString() + ""
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
        }

        return versionCode
    }

    /**
     * get App versionName
     * @param context
     * @return
     */
    @JvmStatic
    fun getVersionName(context: Context): String {
        val packageManager = context.packageManager
        val packageInfo: PackageInfo
        var versionName = ""
        try {
            packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            versionName = packageInfo.versionName
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
        }

        return versionName
    }

    @JvmStatic
    fun isContextValid(context: Context?): Boolean {
        if (context == null) {
            LogUtil.d(TAG, "context is null")
            return false
        }
        if (context is Activity) {
            if (context.isFinishing) {
                LogUtil.d(TAG, "context is finishing")
                return false
            }
            if (context.isDestroyed) {
                LogUtil.d(TAG, "context is destroyed")
                return false
            }
        }
        return true
    }

    fun getColor(context: Context): Int {
        val setting = PreferenceManager.getDefaultSharedPreferences(context)
        val defaultColor = ContextCompat.getColor(context, R.color.colorPrimary)
        val color = setting.getInt("color", defaultColor)
        return if (color != 0 && Color.alpha(color) != 255) {
            defaultColor
        } else {
            color
        }
    }
}
