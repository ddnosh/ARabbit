package com.github.ddnosh.arabbit.util

import com.github.ddnosh.arabbit.module.rxjava.scheduler.ComputationMainScheduler
import com.github.ddnosh.arabbit.module.rxjava.scheduler.IoMainScheduler
import com.github.ddnosh.arabbit.module.rxjava.scheduler.NewThreadMainScheduler
import com.github.ddnosh.arabbit.module.rxjava.scheduler.SingleMainScheduler
import com.github.ddnosh.arabbit.module.rxjava.scheduler.TrampolineMainScheduler

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
