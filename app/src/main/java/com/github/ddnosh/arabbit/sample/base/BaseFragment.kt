package com.github.ddnosh.arabbit.sample.base

import android.content.Context
import android.content.Intent
import com.github.ddnosh.arabbit.ui.base.QuickFragment

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