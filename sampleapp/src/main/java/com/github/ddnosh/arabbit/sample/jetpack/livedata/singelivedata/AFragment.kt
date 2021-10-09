package com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.ddnosh.arabbit.sample.jetpack.livedata.LiveDataViewModel
import com.github.ddnosh.arabbit.ui.base.QuickFragment
import com.github.ddnosh.arabbit.util.LogUtil

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class AFragment : QuickFragment() {

    private val TAG = "AFragment"

    private val mViewModel: LiveDataViewModel by viewModels()

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        mViewModel.liveData.observe(this, {
            LogUtil.d(TAG, it)
        })
    }

    override fun onFirstUserVisible() {
    }

    override fun onUserVisible() {
    }

    override fun onUserInvisible() {
    }
}
