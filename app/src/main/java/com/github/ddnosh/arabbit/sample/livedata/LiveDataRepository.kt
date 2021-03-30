package com.github.ddnosh.arabbit.sample.livedata

import com.github.ddnosh.arabbit.mvvm.livedata.EventMutableLiveData
import com.github.ddnosh.arabbit.mvvm.livedata.LiveDataUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class LiveDataRepository {
    val liveData = EventMutableLiveData<String>()
    fun test() {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    LiveDataUtils.update(liveData, "aha")
                }
    }
}