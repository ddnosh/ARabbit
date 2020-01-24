package com.androidwind.androidquick.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException

/**
 * @author  ddnosh
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
}
