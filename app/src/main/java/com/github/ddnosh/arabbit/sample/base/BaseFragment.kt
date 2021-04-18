package com.github.ddnosh.arabbit.sample.base

import android.content.Context
import android.content.Intent
import com.github.ddnosh.arabbit.ui.base.FrameActivity
import com.github.ddnosh.arabbit.ui.base.QuickFragment

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class BaseFragment : QuickFragment() {

    override fun onFirstUserVisible() {}
    override fun onUserVisible() {}
    override fun onUserInvisible() {}
}