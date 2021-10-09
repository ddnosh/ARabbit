package com.github.ddnosh.arabbit.sample.other

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.base.BaseActivity
import com.github.ddnosh.arabbit.sample.databinding.ActivityTestBinding
import com.github.ddnosh.arabbit.util.LogUtil
import com.github.ddnosh.arabbit.util.ToastUtil.showToast
import com.github.ddnosh.arabbit.util.manager.QActivity.addActivity

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class JavaTestActivity : BaseActivity() {

    private val binding by binding<ActivityTestBinding>()
    override fun attachViewBinding(): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        LogUtil.i(TAG, "")
        showToast("")
        addActivity(this)
        showLoadingDialog()
    }
}
