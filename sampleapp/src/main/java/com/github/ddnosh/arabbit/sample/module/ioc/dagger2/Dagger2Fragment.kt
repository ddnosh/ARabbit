package com.github.ddnosh.arabbit.sample.module.ioc.dagger2

import android.os.Bundle
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.ext.parseState
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentDaggerBinding
import com.github.ddnosh.arabbit.util.LogUtil
import com.github.ddnosh.arabbit.util.ToastUtil
import javax.inject.Inject

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class Dagger2Fragment : BaseFragment() {

    @Inject
    lateinit var user: User
    private val dagger2ViewModel by viewModel<Dagger2ViewModel>()

    private val binding by binding<FragmentDaggerBinding>()
    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        ToastUtil.showToast(user.study())

        dagger2ViewModel.getProjectTitleData().observe(viewLifecycleOwner, { data ->
            parseState(data, {
                it.map {
                    LogUtil.d(TAG, it.name)
                    ToastUtil.showToast(it.name)
                }
            }, {
            })
        })
    }
}
