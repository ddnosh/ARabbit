package com.androidwind.androidquick.ui.viewstatus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class VaryViewHelper(override val view: View?) : IVaryViewHelper {
    private var parentView: ViewGroup? = null
    private var viewIndex: Int = 0
    private var params: ViewGroup.LayoutParams? = null
    override var currentLayout: View? = null
        private set

    override val context: Context
        get() = view!!.context

    private fun init() {
        params = view!!.layoutParams
        if (view.parent != null) {
            parentView = view.parent as ViewGroup
        } else {
            parentView = view.rootView.findViewById<View>(android.R.id.content) as ViewGroup
        }
        val count = parentView!!.childCount
        for (index in 0 until count) {
            if (view === parentView!!.getChildAt(index)) {
                viewIndex = index
                break
            }
        }
        currentLayout = view
    }

    override fun restoreView() {
        showLayout(view)
    }

    override fun showLayout(view: View?) {
        if (parentView == null) {
            init()
        }
        this.currentLayout = view
        // 如果已经是那个view，那就不需要再进行替换操作了
        if (parentView!!.getChildAt(viewIndex) !== view) {
            val parent = view!!.parent as ViewGroup
            parent?.removeView(view)
            parentView!!.removeViewAt(viewIndex)
            parentView!!.addView(view, viewIndex, params)
        }
    }

    override fun inflate(layoutId: Int): View {
        return LayoutInflater.from(view!!.context).inflate(layoutId, null)
    }
}
