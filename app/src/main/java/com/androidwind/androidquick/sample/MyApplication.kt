package com.androidwind.androidquick.sample

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.androidwind.androidquick.sample.util.TimeUtils
import com.androidwind.androidquick.util.ToastUtil


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class MyApplication : MultiDexApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
//        if (isMainProcess()) { //判断主线程
        TimeUtils.beginTimeCalculate(TimeUtils.COLD_START);
//        }
    }

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