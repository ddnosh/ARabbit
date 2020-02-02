package com.androidwind.androidquick.sample

import androidx.multidex.MultiDexApplication
import com.androidwind.androidquick.util.ToastUtil

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class MyApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        //get application
        instance ?: run {
            instance = this
        }
        ToastUtil.register(this)
    }

    companion object {
        @get:Synchronized
        var instance: MyApplication? = null
    }
}