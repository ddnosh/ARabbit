package com.github.ddnosh.arabbit.sample.ui.toolbar

import android.os.Bundle
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseDbFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentToolbarBinding
import com.github.ddnosh.arabbit.sample.other.Title

class ToolBarFragment: BaseDbFragment<FragmentToolbarBinding>() {
    override val contentViewLayoutID: Int = R.layout.fragment_toolbar

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        binding.run {
            title =  Title(R.string.toolbar, R.drawable.icon_arrow_back) { activity?.onBackPressed() }
        }
    }
}