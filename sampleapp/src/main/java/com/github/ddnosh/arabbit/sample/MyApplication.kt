package com.github.ddnosh.arabbit.sample

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDexApplication
import com.github.ddnosh.arabbit.sample.di.DaggerAppComponent
import com.github.ddnosh.arabbit.sample.util.TimeUtils
import com.github.ddnosh.arabbit.util.ToastUtil
import com.tencent.mmkv.MMKV
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class MyApplication :
    MultiDexApplication(),
    HasActivityInjector,
    HasSupportFragmentInjector {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
//        if (isMainProcess()) { //判断主线程
        TimeUtils.beginTimeCalculate(TimeUtils.COLD_START)
//        }
    }

    override fun onCreate() {
        super.onCreate()
        // get application
        instance = this
        MMKV.initialize(this, this.filesDir.absolutePath + "/mmkv")
        ToastUtil.register(this)
        // dagger
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    @Inject
    internal lateinit var dispatchingAndroidInjectorFragment: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjectorFragment
    }

    companion object {
        @get:Synchronized
        lateinit var instance: MyApplication
    }
}
