package com.androidwind.androidquick.module.rxjava

import com.androidwind.androidquick.module.exception.ApiException
import com.androidwind.androidquick.module.exception.ExceptionEngine
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class BaseSubscriber<T> : Subscriber<T> {

    override fun onSubscribe(d: Subscription) {
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        onError(ExceptionEngine.handleException(e))
    }

    override fun onComplete() {}
    abstract fun onError(exception: ApiException)
    abstract fun onSuccess(t: T)
}