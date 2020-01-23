package com.androidwind.androidquick.ui.viewstatus

import android.content.Context
import android.view.View

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
interface IVaryViewHelper {

    val currentLayout: View?

    val context: Context

    val view: View?

    fun restoreView()

    fun showLayout(view: View?)

    fun inflate(layoutId: Int): View
}