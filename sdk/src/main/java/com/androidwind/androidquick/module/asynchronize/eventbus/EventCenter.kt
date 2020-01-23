package com.androidwind.androidquick.module.asynchronize.eventbus


/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class EventCenter<T> {

    var data: T? = null
    var eventCode = -1

    constructor(eventCode: Int) {}

    constructor(eventCode: Int, data: T?) {
        this.eventCode = eventCode
        this.data = data
    }
}
