package com.androidwind.androidquick.util

import com.androidwind.androidquick.module.rxjava.scheduler.IoMainScheduler
import com.cxz.wanandroid.rx.scheduler.ComputationMainScheduler
import com.cxz.wanandroid.rx.scheduler.NewThreadMainScheduler
import com.cxz.wanandroid.rx.scheduler.SingleMainScheduler
import com.cxz.wanandroid.rx.scheduler.TrampolineMainScheduler
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.ComputationScheduler
import io.reactivex.internal.schedulers.TrampolineScheduler
import io.reactivex.schedulers.Schedulers

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object RxUtil {
    /**
     * io2main
     */
    @JvmStatic
    fun <T> io2Main(): IoMainScheduler<T> {
        return IoMainScheduler()
    }

    /**
     * computation2Main
     */
    @JvmStatic
    fun <T> computation2Main(): ComputationMainScheduler<T> {
        return ComputationMainScheduler()
    }

    /**
     * newThread2Main
     */
    @JvmStatic
    fun <T> newThread2Main(): NewThreadMainScheduler<T> {
        return NewThreadMainScheduler()
    }

    /**
     * single2Main
     */
    @JvmStatic
    fun <T> single2Main(): SingleMainScheduler<T> {
        return SingleMainScheduler()
    }

    /**
     * trampoline2Main
     */
    @JvmStatic
    fun <T> trampoline2Main(): TrampolineMainScheduler<T> {
        return TrampolineMainScheduler()
    }
}