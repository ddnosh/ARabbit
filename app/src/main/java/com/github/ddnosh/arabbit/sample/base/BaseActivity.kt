package com.github.ddnosh.arabbit.sample.base

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import com.github.ddnosh.arabbit.ui.base.QuickActivity
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle3.LifecycleProvider

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

    override fun getBundleExtras(extras: Bundle) {

    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        lifecycleProvider = AndroidLifecycle.createLifecycleProvider(this)
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