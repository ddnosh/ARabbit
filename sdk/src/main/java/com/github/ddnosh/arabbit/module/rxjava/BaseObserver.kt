package com.github.ddnosh.arabbit.module.rxjava

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import com.github.ddnosh.arabbit.module.exception.ApiException
import com.github.ddnosh.arabbit.module.exception.ExceptionEngine

/** RxJava订阅者封装,包括Exception
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class BaseObserver<T> : Observer<T> {

    override fun onSubscribe(d: Disposable) {
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
