package com.github.ddnosh.arabbit.sample.jetpack.navigation

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_two.*

class TwoFragment : BaseFragment() {
    override val contentViewLayoutID: Int = R.layout.fragment_two

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        val value = arguments?.getString("key")

        back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}