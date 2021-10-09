package com.github.ddnosh.arabbit.sample.jetpack.navigation

import android.os.Bundle
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentTwoBinding

class TwoFragment : BaseFragment() {
    private val binding by binding<FragmentTwoBinding>()

    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        val value = arguments?.getString("key")

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
