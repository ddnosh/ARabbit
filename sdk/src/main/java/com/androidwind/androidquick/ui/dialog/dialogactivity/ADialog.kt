package com.androidwind.androidquick.ui.dialog.dialogactivity

import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes

import com.androidwind.androidquick.R
import com.androidwind.androidquick.ui.dialog.ViewHolder
import java.io.Serializable

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class ADialog(private val mContext: Context) : BaseDialog(mContext, R.style.dialog_common_style) {

    private val TAG = "ADialog"

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
        //默认对话框UI配置
        if (mLayoutResId == R.layout.dialog_common) {
            holder.setOnClickListener(R.id.dialog_cancel, View.OnClickListener { dialog.dismiss() })
            holder.setOnClickListener(R.id.dialog_confirm, View.OnClickListener { dialog.dismiss() })
        }
        convertListener?.convertView(holder, dialog)
    }
}
