package com.androidwind.androidquick.sample

import android.os.Bundle

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class DemoFragment : BaseFragment() {
    override fun initViewsAndEvents(savedInstanceState: Bundle?) {}
    override val contentViewLayoutID: Int = R.layout.fragment_demo
    override fun isApplyButterKnife(): Boolean = false
}