package com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.jetpack.livedata.LiveDataViewModel
import com.github.ddnosh.arabbit.ui.base.QuickFragment
import com.github.ddnosh.arabbit.util.LogUtil
import kotlinx.android.synthetic.main.fragment_a.*

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
    override val contentViewLayoutID: Int = R.layout.fragment_a
    override fun onFirstUserVisible() {
    }

    override fun onUserVisible() {
    }

    override fun onUserInvisible() {
    }
}