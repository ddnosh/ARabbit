package com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata

import android.os.Bundle
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.appViewModel
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentBBinding
import com.github.ddnosh.arabbit.sample.jetpack.livedata.LiveDataViewModel

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class BFragment : BaseFragment() {
    private val binding by binding<FragmentBBinding>()

    private val activityViewModel by viewModelActivity<LiveDataViewModel>()

    private val fragmentAViewModel by viewModelActivity<AViewModel>()
    private val fragmentBViewModel by viewModelFragment<BViewModel>()

    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        binding.tvB1.text = activityViewModel?.liveData?.value

        appViewModel.info.observe(this, {
            binding.tvB1.text = "Application's ViewModel: $it"
        })

        activityViewModel?.liveData?.observe(this, {
            binding.tvB2.text = "Activity's ViewModel: $it"
        })

        fragmentAViewModel?.a?.observe(this, {
            binding.tvB3.text = "Fragment's ViewModel: $it"
        })

        appViewModel.info.value?.let {
            binding.tvB1.text = "Application's ViewModel: $it"
        }

        activityViewModel?.liveData?.value?.let {
            binding.tvB2.text = "Activity's ViewModel: $it"
        }

        fragmentAViewModel?.a?.value?.let {
            binding.tvB3.text = "Fragment's ViewModel: $it"
        }

        fragmentBViewModel.b.value?.let {
            binding.tvB4.text = "Fragment's ViewModel: $it"
        }

        binding.button1.setOnClickListener {
            appViewModel.info.value = System.currentTimeMillis().toString()
        }

        binding.button2.setOnClickListener {
            fragmentAViewModel?.a?.value = System.currentTimeMillis().toString()
        }
    }
}
