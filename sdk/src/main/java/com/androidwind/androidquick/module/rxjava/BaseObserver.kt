package com.androidwind.androidquick.module.rxjava

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import com.androidwind.androidquick.module.exception.ApiException
import com.androidwind.androidquick.module.exception.ExeceptionEngine

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
        onError(ExeceptionEngine.handleException(e))
    }

    override fun onComplete() {}
    abstract fun onError(exception: ApiException)
    abstract fun onSuccess(t: T)
}
