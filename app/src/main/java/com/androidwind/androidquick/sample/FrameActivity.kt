package com.androidwind.androidquick.sample

import android.os.Bundle
import com.androidwind.androidquick.ui.base.QuickFragment
import com.androidwind.androidquick.util.LogUtil
import com.androidwind.androidquick.util.ReflectUtil

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class FrameActivity : BaseActivity() {
    private var bundle: Bundle? = null
    override val contentViewLayoutID: Int = R.layout.activity_frame

    override fun getBundleExtras(extras: Bundle) {
        bundle = extras
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        val className = bundle!!.getString("fragmentName")
        LogUtil.i(TAG, "the fragment class name is->$className")
        if (className != null) {
            val `object`: Any? = ReflectUtil.getObject(className)
            if (`object` is QuickFragment) {
                val fragment: QuickFragment = `object` as QuickFragment
                if (fragment != null) {
                    supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commitAllowingStateLoss()
                }
            } else {
                LogUtil.e(TAG, " the fragment class is not exist!!!")
            }
        }
    }

    companion object {
        var TAG = "FrameActivity"
    }
}