package com.androidwind.androidquick.ui.dialog.dialogactivity

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

import com.androidwind.androidquick.R
import com.androidwind.androidquick.util.LogUtil
import com.androidwind.androidquick.util.StringUtil

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class CommonDialog(private val mContext: Context) : Dialog(mContext, R.style.dialog_common_style), View.OnClickListener {

    companion object {

        const val TAG = "CommonDialog"
    }

    private var mTitleView: TextView? = null
    private var mInfoView: TextView? = null
    private var mConfirmBtn: Button? = null
    private var mCancelBtn: Button? = null

    private var mTitle: String? = null
    private var mInfoText: String? = null
    private var mConfirmText: String? = null
    private var mCancelText: String? = null
    private var mCallback: DialogCallback? = null
    private var mDialogBtnCallBack: DialogBtnCallBack? = null
    private val mCanConfirmButtonDismiss = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init() //悬浮对话框的情况
    }

    private fun init() {
        setContentView(R.layout.dialog_common)
        mTitleView = findViewById<View>(R.id.dialog_title) as TextView
        mInfoView = findViewById<View>(R.id.dialog_info) as TextView
        mConfirmBtn = findViewById<View>(R.id.dialog_confirm) as Button
        mCancelBtn = findViewById<View>(R.id.dialog_cancel) as Button
        mConfirmBtn!!.setOnClickListener(this)
        mCancelBtn!!.setOnClickListener(this)

        if (mInfoText != null && !StringUtil.isEmpty(mInfoText)) {
            mInfoView!!.text = mInfoText
        }

        //设置title content 等具体名称
        mTitleView!!.text = mTitle

        if (mConfirmText != null && !StringUtil.isEmpty(mConfirmText)) {
            mConfirmBtn!!.text = mConfirmText
        }
        if (mCancelText != null && !StringUtil.isEmpty(mCancelText)) {
            mCancelBtn!!.text = mCancelText
        }
    }

    fun setDialogBtnCallback(dialogBtnCallback: DialogBtnCallBack?) {
        mDialogBtnCallBack = dialogBtnCallback
    }

    fun setValue(
        title: String?, info: String?, confirmBtn: String?, cancelBtn: String?, doNotShowAgain: String?,
        callback: DialogCallback?
    ) {
        LogUtil.i(TAG, "dialog title:$title  mes:0$info positiveBtn:$confirmBtn negativeBtn:$cancelBtn not_tip:$doNotShowAgain callback$callback")
        mTitle = title
        mInfoText = info
        mConfirmText = confirmBtn
        mCancelText = cancelBtn
        mCallback = callback
    }

    fun onDismiss() {
        if (this.isShowing) {
            this.dismiss()
        }
    }

    override fun onClick(view: View) {
        val i = view.id
        if (i == R.id.dialog_confirm) {
            if (mCanConfirmButtonDismiss)
                onDismiss()
            if (mDialogBtnCallBack != null)
                mDialogBtnCallBack!!.onDialogButClick(true)
        } else if (i == R.id.dialog_cancel) {
            onDismiss()
            if (mDialogBtnCallBack != null) {
                mDialogBtnCallBack!!.onDialogButClick(false)
            }
        }
    }

    interface DialogCallback {
        fun onDialogCallback(isConfirm: Boolean, isNottip: Boolean, Position: Int)
    }

    interface DialogBtnCallBack {
        fun onDialogButClick(isConfirm: Boolean)
    }

    class Builder(private val mContext: Context) {
        private var mTitle: String? = null
        private var mInfoText: String? = null
        private var mConfirmText: String? = null
        private var mCancelText: String? = null
        private var mDoNotShowAgain: String? = null
        private var mDialogCallback: DialogCallback? = null
        private var mDialogBtnCallback: DialogBtnCallBack? = null
        private var mSelected = false
        private var mCancelable = true

        fun setCancelable(isCancelable: Boolean): Builder {
            mCancelable = isCancelable
            return this
        }

        fun setTitle(title: String): Builder {
            this.mTitle = title
            return this
        }

        fun setTitle(id: Int): Builder {
            return setTitle(mContext.getString(id))
        }

        fun setMessage(info: String): Builder {
            this.mInfoText = info
            return this
        }

        /***
         * 文本内容是否可以被选择去复制
         */
        fun setContectTextSelected(selected: Boolean): Builder {
            this.mSelected = selected
            return this
        }

        fun setMessage(id: Int): Builder {
            return setMessage(mContext.getString(id))
        }

        /**
         * 设置“确定”按钮的text ,点击监听，通过CommonDialog.DialogCallBack 函数回调监听
         */
        fun setPositiveButton(confirmBtnText: String): Builder {
            this.mConfirmText = confirmBtnText
            return this
        }

        /**
         * 设置“确定”按钮的text ,点击监听，通过CommonDialog.DialogCallBack 函数回调监听
         */
        fun setPositiveButton(id: Int): Builder {
            return setPositiveButton(mContext.getString(id))
        }

        /**
         * 设置“取消”按钮的text ,点击监听，通过CommonDialog.DialogCallBack 函数回调监听
         */
        fun setNegativeButton(cancelBtnText: String): Builder {
            this.mCancelText = cancelBtnText
            return this
        }

        /**
         * 设置“取消”按钮的text ,点击监听，通过CommonDialog.DialogCallBack 函数回调监听
         */
        fun setNegativeButton(id: Int): Builder {
            return setNegativeButton(mContext.getString(id))
        }

        /**
         * 设置“不再提示”的text ,点击监听，通过CommonDialog.DialogCallBack 函数回调监听
         */
        fun setNotTipText(doNotShowAgainText: String): Builder {
            this.mDoNotShowAgain = doNotShowAgainText
            return this
        }

        fun setClickCallBack(dialogCallback: DialogCallback): Builder {
            this.mDialogCallback = dialogCallback
            return this
        }

        fun setBtnClickCallBack(dialogBtnCallBack: DialogBtnCallBack): Builder {
            this.mDialogBtnCallback = dialogBtnCallBack
            return this
        }

        /**
         * 每次调用该函数，都会根据之前设置的各项参数，生成一个新的 commonDialog 对象作为返回,但是该dialog 默认没有show,如果想一步就显示出对话框，可以调用show()方法
         */
        fun create(): CommonDialog {
            val dialog = CommonDialog(mContext)
            dialog.setValue(this.mTitle, this.mInfoText, this.mConfirmText, this.mCancelText, this.mDoNotShowAgain, mDialogCallback)
            dialog.setDialogBtnCallback(mDialogBtnCallback)
            return dialog
        }


        /**
         * 每次调用该函数，都会根据之前设置的各项成熟，生成一个新的 commonDialog 对象作为返回, 该dialog 默认show
         */
        fun show(): CommonDialog {
            LogUtil.i(TAG, " title $mTitle message$mInfoText  show()  to be invoked")
            val dialog = create()
            dialog.show()
            dialog.setCancelable(mCancelable)
            return dialog
        }
    }
}
