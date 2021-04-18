package com.github.ddnosh.arabbit.sample.jetpack.navigation

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_one.*

class OneFragment : BaseFragment() {
    override val contentViewLayoutID: Int = R.layout.fragment_one

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        go.setOnClickListener {
            findNavController().navigate(R.id.action_oneFragment_to_twoFragment)
        }
        goWithArgs.setOnClickListener {
            val bundle = bundleOf("key" to "amount")
            findNavController().navigate(R.id.action_oneFragment_to_twoFragment, bundle)
        }
    }

}