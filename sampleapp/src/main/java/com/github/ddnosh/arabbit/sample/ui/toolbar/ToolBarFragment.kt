package com.github.ddnosh.arabbit.sample.ui.toolbar

import android.os.Bundle
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.databinding.FragmentToolbarBinding
import com.github.ddnosh.arabbit.sample.other.Title
import com.github.ddnosh.arabbit.ui.base.QuickDbFragment

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class ToolBarFragment : QuickDbFragment<FragmentToolbarBinding>() {

    override val contentViewLayoutID: Int = R.layout.fragment_toolbar

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
