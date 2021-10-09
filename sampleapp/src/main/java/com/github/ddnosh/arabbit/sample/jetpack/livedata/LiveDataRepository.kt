package com.github.ddnosh.arabbit.sample.jetpack.livedata

import com.github.ddnosh.arabbit.jetpack.livedata.EventMutableLiveData
import com.github.ddnosh.arabbit.jetpack.livedata.LiveDataUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class LiveDataRepository {
    val liveData = EventMutableLiveData<String>()
    fun test() {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                LiveDataUtils.update(liveData, "aha")
            }
    }
}
