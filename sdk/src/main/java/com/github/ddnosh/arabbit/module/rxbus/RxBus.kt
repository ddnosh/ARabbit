package com.github.ddnosh.arabbit.module.rxbus

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

class RxBus private constructor() {

    companion object {
        private var bus: RxBus? = null
            get() {
                if (field == null) {
                    field = RxBus()
                }
                return field
            }

        fun get(): RxBus {
            return bus ?: RxBus()
        }
    }

    private val bus = PublishSubject.create<Any>()

    fun <T> on(
        eventType: Class<T>,
        owner: LifecycleOwner,
        onNext: Consumer<T>
    ) {
        val disposable = toObservable(eventType)
            .subscribe(onNext)
        toLifeCycle(owner, disposable)
    }

    fun <T> on(
        eventType: Class<T>,
        owner: LifecycleOwner,
        onNext: Consumer<T>,
        onError: Consumer<Throwable>
    ) {
        val disposable = toObservable(eventType).subscribe(onNext, onError)
        toLifeCycle(owner, disposable)
    }

    fun fire(event: Any) {
        bus.onNext(event)
    }

    private fun <T> toObservable(eventType: Class<T>?): Observable<T> {
        return bus.ofType(eventType)
    }

    private fun toLifeCycle(owner: LifecycleOwner, disposable: Disposable) {
        owner.lifecycle.addObserver(
            LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_DESTROY) {
                    disposable.dispose()
                }
            }
        )
    }
}
