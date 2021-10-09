package com.github.ddnosh.arabbit.sample.jetpack.navigation

import android.os.Bundle
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentOneBinding

class OneFragment : BaseFragment() {
    private val binding by binding<FragmentOneBinding>()

    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        binding.go.setOnClickListener {
            findNavController().navigate(R.id.action_oneFragment_to_twoFragment)
        }
        binding.goWithArgs.setOnClickListener {
            val bundle = bundleOf("key" to "amount")
            findNavController().navigate(R.id.action_oneFragment_to_twoFragment, bundle)
        }
    }
}
