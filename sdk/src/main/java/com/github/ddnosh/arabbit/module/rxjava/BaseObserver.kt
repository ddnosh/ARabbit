package com.github.ddnosh.arabbit.module.rxjava

import com.github.ddnosh.arabbit.function.exception.AppException
import com.github.ddnosh.arabbit.function.exception.ExceptionEngine
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

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
    abstract fun onError(exception: AppException)
    abstract fun onSuccess(t: T)
}
