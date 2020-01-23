package com.androidwind.androidquick.sample

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object RxUtil {
    val schedulersTransformer: ObservableTransformer<*, *> = object : ObservableTransformer<Any?, Any?> {
        override fun apply(upstream: Observable<Any?>): ObservableSource<Any?> {
            return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * RxJava线程封装
     */
    fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return schedulersTransformer as ObservableTransformer<T, T>
    }
}