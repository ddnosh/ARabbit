package com.github.ddnosh.arabbit.sample.ui.toolbar

import android.os.Bundle
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.ext.initClose
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentToolbarBinding

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class ToolBarFragment : BaseFragment() {

    private val binding by binding<FragmentToolbarBinding>()

    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        binding.include.mToolbar.initClose("title") {
            activity?.onBackPressed()
        }
    }
}
