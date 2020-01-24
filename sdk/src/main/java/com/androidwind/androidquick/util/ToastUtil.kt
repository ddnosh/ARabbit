package com.androidwind.androidquick.util

import android.content.Context
import android.widget.Toast

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object ToastUtil {

    lateinit var mContext: Context

    @JvmStatic
    fun register(context: Context) {
        mContext = context.applicationContext
    }

    @JvmStatic
    fun showToast(resId: Int) {
        Toast.makeText(mContext, mContext.getString(resId), Toast.LENGTH_SHORT).show()
    }

    @JvmStatic
    fun showToastLong(resId: Int) {
        Toast.makeText(mContext, mContext.getString(resId), Toast.LENGTH_LONG).show()
    }

    @JvmStatic
    fun showToast(msg: String?) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }

    @JvmStatic
    fun showToastLong(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show()
    }
}
