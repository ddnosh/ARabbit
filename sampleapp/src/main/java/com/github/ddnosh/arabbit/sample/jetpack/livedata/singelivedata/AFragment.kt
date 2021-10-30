package com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata

import android.os.Bundle
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.appViewModel
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentABinding
import com.github.ddnosh.arabbit.sample.jetpack.livedata.LiveDataViewModel
import com.github.ddnosh.arabbit.sample.jetpack.viewmodel.AppViewModel

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class AFragment : BaseFragment() {

    private val TAG = "AFragment"

    private val binding by binding<FragmentABinding>()

    private val applicationViewModel by viewModelApplication<AppViewModel>()

    private val activityViewModel by viewModelActivity<LiveDataViewModel>()

    private val fragmentAViewModel by viewModelFragment<AViewModel>()
    private val fragmentBViewModel by viewModelFragment<BViewModel>()

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        appViewModel.info.observe(this, {
            binding.tvA1.text = "Application's ViewModel: $it"
        })

        applicationViewModel.info.observe(this, {
            binding.tvA2.text = "Application's ViewModel: $it"
        })

        activityViewModel?.liveData?.observe(this, {
            binding.tvA3.text = "Activity's ViewModel: $it"
        })

        fragmentAViewModel.a.observe(this, {
            binding.tvA4.text = "Fragment's ViewModel: $it"
        })

        fragmentBViewModel.b.value = "from AFragment"
    }

    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }
}
