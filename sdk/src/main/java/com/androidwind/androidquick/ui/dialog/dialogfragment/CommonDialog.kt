package com.androidwind.androidquick.ui.dialog.dialogfragment

import androidx.annotation.LayoutRes

/**
 * 公用样式Dialog
 */
class CommonDialog : BaseDialogFragment() {
    private var convertListener: ViewConvertListener? = null

    fun setDialogLayout(@LayoutRes layoutId: Int): CommonDialog {
        this.mLayoutResId = layoutId
        return this
    }

    fun setConvertListener(convertListener: ViewConvertListener): CommonDialog {
        this.convertListener = convertListener
        return this
    }

    override fun convertView(holder: ViewHolder, dialog: BaseDialogFragment) {
        if (convertListener != null) {
            convertListener!!.convertView(holder, dialog)
        }
    }

    companion object {

        fun newInstance(): CommonDialog {
            return CommonDialog()
        }
    }
}
