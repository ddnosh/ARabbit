package com.github.ddnosh.arabbit.sample.jetpack.navigation

import android.os.Bundle
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentNavigationBinding

class NavigationFragment : BaseFragment() {

    private val binding by binding<FragmentNavigationBinding>()

    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
    }
}
