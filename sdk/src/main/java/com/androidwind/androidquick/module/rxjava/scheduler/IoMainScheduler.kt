package com.androidwind.androidquick.module.rxjava.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class IoMainScheduler<T> : BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())