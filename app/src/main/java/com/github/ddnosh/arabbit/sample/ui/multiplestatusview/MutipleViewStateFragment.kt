package com.github.ddnosh.arabbit.sample.ui.multiplestatusview

import android.os.Bundle
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_multiple_status_view.*

class MutipleViewStateFragment: BaseFragment() {
    override val contentViewLayoutID: Int = R.layout.fragment_multiple_status_view

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        multiple_status_view.setOnClickListener {
            ToastUtil.showToast("retried.")
        }
    }
}