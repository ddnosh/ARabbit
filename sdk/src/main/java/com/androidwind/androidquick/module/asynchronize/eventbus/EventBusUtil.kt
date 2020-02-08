package com.androidwind.androidquick.module.asynchronize.eventbus

import org.greenrobot.eventbus.EventBus

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object EventBusUtil {
    @JvmStatic
    fun register(subscriber: Any) {
        EventBus.getDefault().register(subscriber)
    }

    @JvmStatic
    fun unregister(subscriber: Any) {
        EventBus.getDefault().unregister(subscriber)
    }

    @JvmStatic
    fun sendEvent(event: EventCenter<Any>?) {
        EventBus.getDefault().post(event)
    }

    @JvmStatic
    fun sendStickyEvent(event: EventCenter<Any>?) {
        EventBus.getDefault().postSticky(event)
    }
}