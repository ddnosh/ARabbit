package com.github.ddnosh.arabbit.module.rxjava.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class SingleMainScheduler<T> : BaseScheduler<T>(Schedulers.single(), AndroidSchedulers.mainThread())
