package com.androidwind.androidquick.util

import android.text.TextUtils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import org.json.JSONException
import org.json.JSONObject

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object GsonUtil {

    private val TAG = "GsonUtil"

    private var gson: Gson? = null

    init {
        if (gson == null) {
            gson = Gson()
        }
    }

    /**
     * 返回指定key的值
     *
     * @param json
     * @param key
     * @param type 数据类型0表示String类，1表示int类型
     * @return
     */
    fun getField(json: String, key: String, type: Int): Any? {
        if (StringUtil.isEmpty(json) || StringUtil.isEmpty(key))
            return null
        try {
            val `object` = JSONObject(json)
            if (`object`.has(key)) {
                return if (type == 0) {
                    `object`.getString(key)
                } else {
                    `object`.getInt(key)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return null
    }

    fun <T> fromJson(JSONString: String, typeToken: TypeToken<T>): T? {
        if (TextUtils.isEmpty(JSONString))
            return null

        var t: T? = null
        try {
            t = gson!!.fromJson<T>(JSONString, typeToken.type)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return t
    }

    fun <T> fromJson(JSONString: String, classOfT: Class<T>): T? {
        if (TextUtils.isEmpty(JSONString))
            return null

        var t: T? = null
        try {
            t = gson!!.fromJson(JSONString, classOfT)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return t
    }

    fun toJsonString(`object`: Any): String? {
        var gsonString: String? = null
        if (gson != null) {
            gsonString = gson!!.toJson(`object`)
        }
        return gsonString
    }
}
