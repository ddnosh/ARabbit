package com.androidwind.androidquick.sample

import android.content.Context
import android.content.Intent
import android.view.View
import com.androidwind.androidquick.module.asynchronize.eventbus.EventCenter
import com.androidwind.androidquick.ui.base.QuickFragment

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class BaseFragment : QuickFragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onFirstUserVisible() {}
    override fun onUserVisible() {}
    override fun onUserInvisible() {}
    override fun setDefaultVaryViewRoot(): View? = null

    override val isBindEventBus: Boolean = false

    protected override fun onEventComing(eventCenter: EventCenter<*>) {}
    override fun getGoIntent(clazz: Class<*>): Intent {
        return if (BaseFragment::class.java.isAssignableFrom(clazz)) {
            val intent = Intent(activity, FrameActivity::class.java)
            intent.putExtra("fragmentName", clazz.name)
            intent
        } else {
            super.getGoIntent(clazz)
        }
    }
}