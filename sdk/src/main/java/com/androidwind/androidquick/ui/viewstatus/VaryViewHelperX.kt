package com.androidwind.androidquick.ui.viewstatus

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.FrameLayout

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class VaryViewHelperX(override val view: View) : IVaryViewHelper {

    private val helper: IVaryViewHelper

    override val currentLayout: View?
        get() = helper.currentLayout

    override val context: Context
        get() = helper.context

    init {
        val group = view.parent as ViewGroup
        val layoutParams = view.layoutParams
        val frameLayout = FrameLayout(view.context)
        group.removeView(view)
        group.addView(frameLayout, layoutParams)

        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        val floatView = View(view.context)
        frameLayout.addView(view, params)
        frameLayout.addView(floatView, params)
        helper = VaryViewHelper(floatView)
    }

    override fun restoreView() {
        helper.restoreView()
    }

    override fun showLayout(view: View?) {
        helper.showLayout(view)
    }

    override fun inflate(layoutId: Int): View {
        return helper.inflate(layoutId)
    }
}
