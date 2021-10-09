package com.github.ddnosh.arabbit.sample.ui.multiplestatusview

import android.os.Bundle
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentMultipleStatusViewBinding

class MultipleViewStateFragment : BaseFragment() {

    private val binding by binding<FragmentMultipleStatusViewBinding>()

    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        binding.btnUiPagestatusLoading.setOnClickListener {
            binding.multipleStatusView.showLoading()
        }
        binding.btnUiPagestatusEmpty.setOnClickListener {
            binding.multipleStatusView.showEmpty()
        }
        binding.btnUiPagestatusError.setOnClickListener {
            binding.multipleStatusView.showError()
        }
        binding.btnUiPagestatusNetworkerror.setOnClickListener {
            binding.multipleStatusView.showNoNetwork()
        }

    }
}
