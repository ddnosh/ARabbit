package com.androidwind.androidquick.module.asynchronize.eventbus

import org.greenrobot.eventbus.EventBus

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object EventBusUtil {
    fun register(subscriber: Any) {
        EventBus.getDefault().register(subscriber)
    }

    fun unregister(subscriber: Any) {
        EventBus.getDefault().unregister(subscriber)
    }

    fun sendEvent(event: EventCenter<Any>?) {
        EventBus.getDefault().post(event)
    }

    fun sendStickyEvent(event: EventCenter<Any>?) {
        EventBus.getDefault().postSticky(event)
    }
}