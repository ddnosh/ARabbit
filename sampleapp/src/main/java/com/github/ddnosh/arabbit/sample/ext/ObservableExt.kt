package com.github.ddnosh.arabbit.sample.ext

import androidx.lifecycle.Lifecycle
import com.github.ddnosh.arabbit.util.RxUtil
import com.trello.rxlifecycle3.LifecycleProvider
import io.reactivex.Observable

fun <T> Observable<T>.composeWithLifecycleDestroy(lifecycleProvider: LifecycleProvider<Lifecycle.Event>): Observable<T> {
    return this
        .compose(RxUtil.io2Main())
        .compose(lifecycleProvider.bindUntilEvent(Lifecycle.Event.ON_DESTROY))
}
