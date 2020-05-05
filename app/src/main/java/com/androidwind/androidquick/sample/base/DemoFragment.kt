package com.androidwind.androidquick.sample.base

import android.os.Bundle

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class DemoFragment : BaseFragment() {
    override fun initViewsAndEvents(savedInstanceState: Bundle?) {}
    override val contentViewLayoutID: Int = com.androidwind.androidquick.sample.R.layout.fragment_demo
    override fun isApplyButterKnife(): Boolean = false
}