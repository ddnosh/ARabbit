package com.github.ddnosh.arabbit.sample.multiplestatusview

import android.os.Bundle
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseActivity
import com.github.ddnosh.arabbit.util.ToastUtil
import kotlinx.android.synthetic.main.activity_msv.*

class MSVActivity: BaseActivity() {
    override val contentViewLayoutID: Int = R.layout.activity_msv

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        super.initViewsAndEvents(savedInstanceState)
        multiple_status_view.setOnClickListener {
            ToastUtil.showToast("retried.")
        }
    }
}