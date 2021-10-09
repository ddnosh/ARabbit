package com.github.ddnosh.arabbit.ui.base

import android.os.Bundle
import com.github.ddnosh.arabbit.R
import com.github.ddnosh.arabbit.util.LogUtil
import com.github.ddnosh.arabbit.util.ReflectUtil

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class FrameActivity : QuickActivity() {

    companion object {
        var TAG = "FrameActivity"
    }

    override val contentViewLayoutID: Int = R.layout.activity_frame

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        val className = intent.extras?.getString("fragmentName")
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
