package com.github.ddnosh.arabbit.ui.dialog.dialogfragment

import android.view.View
import androidx.annotation.LayoutRes
import com.github.ddnosh.arabbit.R
import com.github.ddnosh.arabbit.ui.dialog.ViewHolder

/**
 * 公用样式Dialog
 */
class FDialog : BaseDialogFragment() {

    private val TAG = "FDialog"

    init {
        mLayoutResId = R.layout.dialogfragment_confirm
    }

    private var convertListener: ViewConvertListener? = null

    fun setDialogLayout(@LayoutRes layoutId: Int): FDialog {
        this.mLayoutResId = layoutId
        return this
    }

    fun setConvertListener(convertListener: ViewConvertListener): FDialog {
        this.convertListener = convertListener
        return this
    }

    override fun convertView(holder: ViewHolder, dialog: BaseDialogFragment) {
        // 默认对话框UI配置
        if (mLayoutResId == R.layout.dialogfragment_confirm) {
            holder.setOnClickListener(R.id.df_cancel, View.OnClickListener { dialog.dismiss() })
            holder.setOnClickListener(R.id.df_confirm, View.OnClickListener { dialog.dismiss() })
        }
        convertListener?.convertView(holder, dialog)
    }
}
