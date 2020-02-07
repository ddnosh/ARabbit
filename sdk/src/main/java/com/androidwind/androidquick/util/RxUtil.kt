package com.androidwind.androidquick.util

import com.androidwind.androidquick.module.rxjava.exception.ExceptionScheduler
import com.androidwind.androidquick.module.rxjava.scheduler.IoMainScheduler
import com.androidwind.androidquick.module.rxjava.scheduler.ComputationMainScheduler
import com.androidwind.androidquick.module.rxjava.scheduler.NewThreadMainScheduler
import com.androidwind.androidquick.module.rxjava.scheduler.SingleMainScheduler
import com.androidwind.androidquick.module.rxjava.scheduler.TrampolineMainScheduler

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

    /**
     *
     */
    @JvmStatic
    fun <T> exception(): ExceptionScheduler<T> {
        return ExceptionScheduler()
    }
}