package com.androidwind.androidquick.sample

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.androidwind.androidquick.ui.dialog.ViewHolder
import com.androidwind.androidquick.ui.dialog.dialogactivity.BaseDialog
import com.androidwind.androidquick.ui.dialog.dialogactivity.ADialog
import com.androidwind.androidquick.ui.dialog.dialogfragment.BaseDialogFragment
import com.androidwind.androidquick.ui.dialog.dialogfragment.FDialog

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class DialogActivity : BaseActivity() {
    override val contentViewLayoutID: Int = R.layout.activity_dialog

    override fun getBundleExtras(extras: Bundle) {}

    fun dialog1(view: View) {
        ADialog(mContext)
//            .setDialogLayout(R.layout.dialog_alert)
//            .setConvertListener(object : CommonDialog.ViewConvertListener {
//                override fun convertView(holder: ViewHolder, dialog: BaseDialog) {
//                    holder.getView<TextView>(R.id.dialog_title).setText("QuickBase")
//                    holder.getView<TextView>(R.id.dialog_info).setText("this is an alert message.")
//                    holder.getView<TextView>(R.id.dialog_confirm).setText("Close")
//                    holder.setOnClickListener(R.id.dialog_confirm, View.OnClickListener { dialog.dismiss() })
//                }
//            })
            .show();
    }

    fun dialog2(view: View) {
        ADialog(mContext)
            .setDialogLayout(R.layout.dialog_loading)
            .setConvertListener(object : BaseDialog.ViewConvertListener {
                override fun convertView(holder: ViewHolder, dialog: BaseDialog) {
                    holder.getView<TextView>(R.id.tip).text = "正在努力加载..."
                }
            }).show()
    }

    fun dialog3(view: View) {
        FDialog()
            .setDialogLayout(R.layout.dialogfragment_confirm)
            .setConvertListener(object : BaseDialogFragment.ViewConvertListener {
                override fun convertView(holder: ViewHolder, dialog: BaseDialogFragment) {
                    holder.getView<TextView>(R.id.df_title).text = "Title"
                    holder.getView<TextView>(R.id.df_message).text = "This is content!"
                    holder.setOnClickListener(R.id.df_confirm, View.OnClickListener { dialog.dismiss(); })
                    holder.setOnClickListener(R.id.df_cancel, View.OnClickListener { dialog.dismiss(); })
                }
            })
            .setMargin(60)
            .setOutCancel(true)
            .show(getSupportFragmentManager());
    }
}