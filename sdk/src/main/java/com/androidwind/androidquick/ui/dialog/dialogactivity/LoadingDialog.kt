package com.androidwind.androidquick.ui.dialog.dialogactivity

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import com.androidwind.androidquick.R
import com.androidwind.androidquick.util.StringUtil

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class LoadingDialog(val loadingDialogContext: Context) : Dialog(loadingDialogContext, R.style.dialog_loading_view), View.OnClickListener {

    companion object {

        const val TAG = "LoadingDialog"
    }

    private var mNoBgLinely: LinearLayout? = null
    private var mTipTxt: TextView? = null
    private var mTip: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        mNoBgLinely = findViewById<View>(R.id.ll_loading) as LinearLayout
        mNoBgLinely!!.visibility = View.VISIBLE
        mTipTxt = findViewById<View>(R.id.tip) as TextView
        if (!StringUtil.isEmpty(mTip)) {
            mTipTxt!!.text = mTip
        }
    }

    fun setTip(tip: String?) {
        mTip = tip
    }

    fun onDismiss() {
        if (this.isShowing) {
            this.dismiss()
        }
    }

    override fun onClick(v: View) {
        onDismiss()
    }

    interface LoadingDialogCallback {
        fun onDialogCallback(type: Int, id: String)
    }
}
