package com.github.ddnosh.arabbit.util.handler

import android.os.Handler
import android.os.Message

import java.lang.ref.WeakReference

/**
 * 防止内存泄漏的Handler
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class SafeHandler<T>(view: T) : Handler() {

    private var mView: WeakReference<T> = WeakReference(view)

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        val view = mView.get()
        view?.let {
            disposeMessage(it, msg)
        }
    }

    abstract fun disposeMessage(t: T, msg: Message)
}
