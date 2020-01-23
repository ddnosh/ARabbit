package com.androidwind.androidquick.ui.viewstatus


import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.androidwind.androidquick.R
import com.androidwind.androidquick.util.StringUtil

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class VaryViewHelperController(private val helper: IVaryViewHelper) {

    constructor(view: View?) : this(VaryViewHelper(view)) {}

    fun showNetworkError(onClickListener: View.OnClickListener?) {
        val layout = helper.inflate(R.layout.view_status)
        val textView = layout.findViewById<View>(R.id.message_info) as TextView
        textView.text = helper.context.resources.getString(R.string.common_no_network_msg)

        val imageView = layout.findViewById<View>(R.id.message_icon) as ImageView
        imageView.setImageResource(R.drawable.ic_exception)

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener)
        }

        helper.showLayout(layout)
    }

    fun showError(errorMsg: String, onClickListener: View.OnClickListener?) {
        val layout = helper.inflate(R.layout.view_status)
        val textView = layout.findViewById<View>(R.id.message_info) as TextView
        if (!StringUtil.isEmpty(errorMsg)) {
            textView.text = errorMsg
        } else {
            textView.text = helper.context.resources.getString(R.string.common_error_msg)
        }

        val imageView = layout.findViewById<View>(R.id.message_icon) as ImageView
        imageView.setImageResource(R.drawable.ic_error)

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener)
        }

        helper.showLayout(layout)
    }

    fun showEmpty(emptyMsg: String, onClickListener: View.OnClickListener?) {
        val layout = helper.inflate(R.layout.view_status)
        val textView = layout.findViewById<View>(R.id.message_info) as TextView
        if (!StringUtil.isEmpty(emptyMsg)) {
            textView.text = emptyMsg
        } else {
            textView.text = helper.context.resources.getString(R.string.common_empty_msg)
        }

        val imageView = layout.findViewById<View>(R.id.message_icon) as ImageView
        imageView.setImageResource(R.drawable.ic_exception)

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener)
        }

        helper.showLayout(layout)
    }

    fun showLoading(msg: String) {
        val layout = helper.inflate(R.layout.view_status)
        if (!StringUtil.isEmpty(msg)) {
            val textView = layout.findViewById<View>(R.id.message_info) as TextView
            textView.text = msg
        }
        helper.showLayout(layout)
    }

    fun restore() {
        helper.restoreView()
    }
}
