package com.github.ddnosh.arabbit.sample.other

import android.os.Bundle
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseActivity
import com.github.ddnosh.arabbit.util.LogUtil
import com.github.ddnosh.arabbit.util.ToastUtil.showToast
import com.github.ddnosh.arabbit.util.manager.QActivity.addActivity

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class JavaTestActivity : BaseActivity() {
    override val contentViewLayoutID: Int = R.layout.activity_test

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        LogUtil.i(TAG, "")
        showToast("")
        addActivity(this)
        showLoadingDialog()
    }
}