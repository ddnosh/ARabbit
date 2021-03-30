package com.github.ddnosh.arabbit.sample

import android.view.View
import androidx.lifecycle.Lifecycle
import com.github.ddnosh.arabbit.util.RxUtil
import com.trello.rxlifecycle3.LifecycleProvider
import io.reactivex.Observable

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

//自定义方法
fun <T> Observable<T>.composeWithLifecycleDestroy(lifecycleProvider: LifecycleProvider<Lifecycle.Event>): Observable<T> {
    return this
            .compose(RxUtil.io2Main())
            .compose(lifecycleProvider.bindUntilEvent(Lifecycle.Event.ON_DESTROY))
}

//自定义属性
var <T : View> T.getClickTime: Long?
    get() {
        val tag = getTag(100) as Long?
        return tag?.run {
            this
        } ?: run {
            0L
        }
    }
    set(value) = setTag(100, System.currentTimeMillis())