package com.androidwind.androidquick.util

import android.content.Context
import android.content.SharedPreferences

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object SpUtil {
    val PREF_FILE_NAME = "android_pref_file"

    lateinit var mContext: Context

    @JvmStatic
    fun init(context: Context) {
        mContext = context.applicationContext
    }

    /**
     * 清除所有数据
     */
    @JvmOverloads
    @JvmStatic
    fun clear(file: String = PREF_FILE_NAME) {
        val sp = mContext.getSharedPreferences(file, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.clear().apply()
    }

    /**
     * 保存数据
     */
    @JvmStatic
    fun setParam(key: String, `object`: Any) {
        setParam(PREF_FILE_NAME, key, `object`)
    }

    @JvmStatic
    fun setParam(file: String, key: String, `object`: Any) {

        val type = `object`.javaClass.simpleName

        val sp = mContext.getSharedPreferences(file,
            Context.MODE_PRIVATE)
        val editor = sp.edit()

        if ("String" == type) {
            editor.putString(key, `object` as String)
        } else if ("Integer" == type) {
            editor.putInt(key, `object` as Int)
        } else if ("Boolean" == type) {
            editor.putBoolean(key, `object` as Boolean)
        } else if ("Float" == type) {
            editor.putFloat(key, `object` as Float)
        } else if ("Long" == type) {
            editor.putLong(key, `object` as Long)
        } else {
            editor.putString(key, `object`.toString())
        }

        editor.apply()
    }

    /**
     * 查询数据
     */
    @JvmStatic
    fun getParam(key: String, defaultObject: Any): Any? {
        return getParam(PREF_FILE_NAME, key, defaultObject)
    }

    @JvmStatic
    fun getParam(file: String, key: String, defaultObject: Any?): Any? {
        if (defaultObject == null) {
            return null
        }
        try {
            val type = defaultObject.javaClass.simpleName

            val sp = mContext.getSharedPreferences(file,
                Context.MODE_PRIVATE)

            if ("String" == type) {
                return sp.getString(key, defaultObject as String?)
            } else if ("Integer" == type) {
                return sp.getInt(key, (defaultObject as Int?)!!)
            } else if ("Boolean" == type) {
                return sp.getBoolean(key, (defaultObject as Boolean?)!!)
            } else if ("Float" == type) {
                return sp.getFloat(key, (defaultObject as Float?)!!)
            } else if ("Long" == type) {
                return sp.getLong(key, (defaultObject as Long?)!!)
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * 移除某个key值已经对应的值
     */
    @JvmStatic
    fun remove(context: Context, key: String) {
        remove(context, PREF_FILE_NAME, key)
    }

    @JvmStatic
    fun remove(context: Context, file: String, key: String) {
        val sp = context.getSharedPreferences(file,
            Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.remove(key)
        editor.apply()
    }
}
