package com.github.ddnosh.arabbit.sample

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.github.ddnosh.arabbit.sample.util.TimeUtils
import com.github.ddnosh.arabbit.util.ToastUtil
import com.tencent.mmkv.MMKV


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
        instance = this
        MMKV.initialize(this.filesDir.absolutePath + "/mmkv")
        ToastUtil.register(this)
    }

    companion object {
        @get:Synchronized
        lateinit var instance: MyApplication
    }
}