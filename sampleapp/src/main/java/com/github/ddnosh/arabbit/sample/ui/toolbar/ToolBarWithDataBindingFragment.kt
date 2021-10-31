package com.github.ddnosh.arabbit.sample.ui.toolbar

import android.os.Bundle
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.databinding.FragmentToolbarDatabindingBinding
import com.github.ddnosh.arabbit.sample.other.Title
import com.github.ddnosh.arabbit.ui.base.QuickDbFragment

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class ToolBarWithDataBindingFragment : QuickDbFragment<FragmentToolbarDatabindingBinding>() {

    override val contentViewLayoutID: Int = R.layout.fragment_toolbar_databinding

    override fun onFirstUserVisible() {
    }

    override fun onUserVisible() {
    }

    override fun onUserInvisible() {
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        binding.run {
            title =
                Title(R.string.toolbar, R.drawable.icon_arrow_back) { activity?.onBackPressed() }
        }
    }
}
