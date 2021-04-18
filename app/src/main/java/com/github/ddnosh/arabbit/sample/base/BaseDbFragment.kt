package com.github.ddnosh.arabbit.sample.base

import androidx.databinding.ViewDataBinding
import com.github.ddnosh.arabbit.ui.base.QuickDbFragment

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class BaseDbFragment<T: ViewDataBinding> : QuickDbFragment<T>() {

    override fun onFirstUserVisible() {}
    override fun onUserVisible() {}
    override fun onUserInvisible() {}
}