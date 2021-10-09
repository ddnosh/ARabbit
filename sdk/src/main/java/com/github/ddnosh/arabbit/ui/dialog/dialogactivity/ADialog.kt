package com.github.ddnosh.arabbit.ui.dialog.dialogactivity

import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes
import com.github.ddnosh.arabbit.R
import com.github.ddnosh.arabbit.ui.dialog.ViewHolder

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class ADialog(mContext: Context) : BaseDialog(mContext, R.style.dialog_common_style) {

    init {
        mLayoutResId = R.layout.dialog_common
    }

    private var convertListener: ViewConvertListener? = null

    fun setDialogLayout(@LayoutRes layoutId: Int): ADialog {
        this.mLayoutResId = layoutId
        return this
    }

    fun setConvertListener(convertListener: ViewConvertListener): ADialog {
        this.convertListener = convertListener
        return this
    }

    override fun convertView(holder: ViewHolder, dialog: BaseDialog) {
        // 默认对话框UI配置
        if (mLayoutResId == R.layout.dialog_common) {
            holder.setOnClickListener(R.id.dialog_cancel, View.OnClickListener { dialog.dismiss() })
            holder.setOnClickListener(R.id.dialog_confirm, View.OnClickListener { dialog.dismiss() })
        }
        convertListener?.convertView(holder, dialog)
    }
}
