package com.github.ddnosh.arabbit.sample.base

import android.os.Bundle
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.ui.base.QuickFragment
import com.github.ddnosh.arabbit.util.LogUtil
import com.github.ddnosh.arabbit.util.ReflectUtil

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class FrameActivity : BaseActivity() {

    companion object {
        var TAG = "FrameActivity"
    }

    private var bundle: Bundle? = null
    override val contentViewLayoutID: Int = R.layout.activity_frame

    override fun getBundleExtras(extras: Bundle) {
        bundle = extras
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        val className = bundle?.getString("fragmentName")
        LogUtil.i(TAG, "the fragment class name is->$className")
        if (className != null) {
            val `object`: Any? = ReflectUtil.getObject(className)
            if (`object` is QuickFragment) {
                val fragment: QuickFragment = `object` as QuickFragment
                fragment.run {
                    supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commitAllowingStateLoss()
                }
            } else {
                LogUtil.e(TAG, " the fragment class is not exist!!!")
            }
        }
    }
}