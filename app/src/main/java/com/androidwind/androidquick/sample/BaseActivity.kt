package com.androidwind.androidquick.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import com.androidwind.androidquick.module.asynchronize.eventbus.EventCenter
import com.androidwind.androidquick.ui.base.QuickActivity
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * 通用抽象方法的实现集合
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class BaseActivity : QuickActivity() {
    companion object {
        @JvmField
        var TAG = "BaseActivity"
    }

    protected lateinit var lifecycleProvider: LifecycleProvider<Lifecycle.Event>

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        lifecycleProvider = AndroidLifecycle.createLifecycleProvider(this)
    }

    override val isBindEventBus: Boolean = false

    override fun setDefaultVaryViewRoot(): View? = null

    override fun onEventComing(eventCenter: EventCenter<*>) {
        Log.i(TAG, eventCenter.eventCode.toString())
    }

    override fun toggleOverridePendingTransition(): Boolean = true

    override val overridePendingTransitionMode: TransitionMode = TransitionMode.LEFT

    override val isLoadDefaultTitleBar: Boolean = true

    override fun getGoIntent(clazz: Class<*>): Intent {
        return if (BaseFragment::class.java.isAssignableFrom(clazz)) {
            val intent = Intent(this, FrameActivity::class.java)
            intent.putExtra("fragmentName", clazz.name)
            intent
        } else {
            super.getGoIntent(clazz)
        }
    }
}