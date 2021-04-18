package com.github.ddnosh.arabbit.ext

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.github.ddnosh.arabbit.module.rxbus.RxBus
import com.github.ddnosh.arabbit.util.RxUtil
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.kotlin.bindUntilEvent
import io.reactivex.Observable

fun <T> Observable<T>.composeWithLifecycleDestroy(lifecycleProvider: LifecycleProvider<Lifecycle.Event>): Observable<T> {
    return this
            .compose(RxUtil.io2Main())
            .compose(lifecycleProvider.bindUntilEvent(Lifecycle.Event.ON_DESTROY))
}

fun <T> RxBus.toSafeObservable(eventType: Class<T>, lifecycleProvider: LifecycleProvider<Lifecycle.Event>, owner: LifecycleOwner): Observable<T> {
    return this.toObservable(eventType)
            .bindLifeOwner(owner)
            .compose(RxUtil.io2Main())
            .bindUntilEvent(lifecycleProvider, Lifecycle.Event.ON_DESTROY)
}