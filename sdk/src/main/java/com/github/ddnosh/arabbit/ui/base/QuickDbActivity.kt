package com.github.ddnosh.arabbit.ui.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class QuickDbActivity<DB : ViewDataBinding> : QuickActivity() {

    companion object {
        @JvmField
        var TAG = "QuickActivityVB"
    }

    protected lateinit var binding: DB

    override fun initContentView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
    }
}
