package com.github.ddnosh.arabbit.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class QuickDbFragment<DB : ViewDataBinding> : QuickFragment() {

    companion object {
        @JvmField
        var TAG = "QuickDbFragment"
    }

    protected lateinit var binding: DB

    override fun initContentView(layoutId: Int, inflater: LayoutInflater, container: ViewGroup?): View {
        return try {
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            binding.root
        } catch (e: NoClassDefFoundError) {
            inflater.inflate(layoutId, container, false)
        }
    }
}
