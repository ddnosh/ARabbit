package com.androidwind.androidquick.sample

import androidx.multidex.MultiDexApplication

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
    }

    companion object {
        @get:Synchronized
        var instance: MyApplication? = null
    }
}