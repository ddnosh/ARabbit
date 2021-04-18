package com.github.ddnosh.arabbit.sample.jetpack.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.github.ddnosh.arabbit.ext.launchIO
import com.github.ddnosh.arabbit.module.rxbus.RxBus
import com.github.ddnosh.arabbit.jetpack.viewmodel.BaseAndroidViewModel
import com.github.ddnosh.arabbit.sample.module.rxbus.TestEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

//viewmode配合kotlin的coroutine, 推荐这种做法
class LogoutAndroidViewModel(application: Application) : BaseAndroidViewModel(application) {
    fun logout(): MutableLiveData<Boolean> {
        val logoutData = MutableLiveData<Boolean>()
        //进行一些验证
//        if (验证未通过) {
//            data.setValue(false);
//            return data;
//        }

        //使用kotlin的coroutine
        launchIO({
            RxBus.getDefault().post(TestEvent("logout"))
            delay(3000)
            withContext(Dispatchers.Main) {
                logoutData.value = true
            }
        })

        return logoutData
    }
}