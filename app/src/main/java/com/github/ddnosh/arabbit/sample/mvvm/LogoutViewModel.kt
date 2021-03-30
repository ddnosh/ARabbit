package com.github.ddnosh.arabbit.sample.mvvm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.github.ddnosh.arabbit.module.rxbus.RxBus
import com.github.ddnosh.arabbit.sample.event.TestEvent
import com.github.ddnosh.arabbit.util.RxUtil.io2Main
import com.uber.autodispose.autoDisposable
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.Exception

//使用Android Jetpack组件: ViewModel（Kotlin）
class LogoutViewModel(application: Application) : BaseViewModel(application) {
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