package com.github.ddnosh.arabbit.sample.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.databinding.FragmentSampleBinding
import com.github.ddnosh.arabbit.sample.other.Title
import com.github.ddnosh.arabbit.ui.adapter.CommonAdapter
import com.github.ddnosh.arabbit.ui.adapter.CommonViewHolder
import com.github.ddnosh.arabbit.ui.base.QuickDbFragment
import com.github.ddnosh.arabbit.ui.dialog.ViewHolder
import com.github.ddnosh.arabbit.ui.dialog.dialogactivity.ADialog
import com.github.ddnosh.arabbit.ui.dialog.dialogactivity.BaseDialog
import com.github.ddnosh.arabbit.ui.dialog.dialogfragment.BaseDialogFragment
import com.github.ddnosh.arabbit.ui.dialog.dialogfragment.FDialog
import com.github.ddnosh.arabbit.util.LogUtil

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class DialogFragment : QuickDbFragment<FragmentSampleBinding>() {

    override val contentViewLayoutID: Int = R.layout.fragment_sample

    private var dialogNameList = arrayListOf("1.activity:dialog1", "2.activity:dialog2", "3.fragment:dialog1")

    override fun onFirstUserVisible() {
    }
    override fun onUserVisible() {
    }
    override fun onUserInvisible() {
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        binding.run {
            title = Title(R.string.dialog, R.drawable.icon_arrow_back) { activity?.onBackPressed() }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = object : CommonAdapter<String>(requireActivity(), R.layout.sample_item, dialogNameList) {
            override fun convert(holder: CommonViewHolder, name: String) {
                holder.setText(R.id.sample_text, name)
                holder.setOnClickListener(R.id.item_root) {
                    LogUtil.d(TAG, "onItemClick:$name")
                    when (name) {
                        "1.activity:dialog1" -> dialog1()
                        "2.activity:dialog2" -> dialog2()
                        "3.fragment:dialog1" -> dialog3()
                    }
                }
            }
        }
    }

    fun dialog1() {
        ADialog(mContext)
            .setDialogLayout(R.layout.dialog_alert)
            .setConvertListener(object : BaseDialog.ViewConvertListener {
                override fun convertView(holder: ViewHolder, dialog: BaseDialog) {
                    holder.setText(R.id.dialog_title, "ARabbit")
                    holder.setText(R.id.dialog_info, "this is an alert message.")
                    holder.setText(R.id.dialog_confirm, "Close")
                    holder.setOnClickListener(R.id.dialog_confirm, View.OnClickListener { dialog.dismiss() })
                }
            })
            .show()
    }

    fun dialog2() {
        ADialog(mContext)
            .setDialogLayout(R.layout.dialog_loading)
            .setConvertListener(object : BaseDialog.ViewConvertListener {
                override fun convertView(holder: ViewHolder, dialog: BaseDialog) {
                    holder.setText(R.id.tip, "正在努力加载...")
                }
            }).show()
    }

    fun dialog3() {
        fragmentManager?.let {
            FDialog()
                .setDialogLayout(R.layout.dialogfragment_confirm)
                .setConvertListener(object : BaseDialogFragment.ViewConvertListener {
                    override fun convertView(holder: ViewHolder, dialog: BaseDialogFragment) {
                        holder.setText(R.id.df_title, "Title")
                        holder.setText(R.id.df_message, "This is content!")
                        holder.setOnClickListener(R.id.df_confirm, View.OnClickListener { dialog.dismiss(); })
                        holder.setOnClickListener(R.id.df_cancel, View.OnClickListener { dialog.dismiss(); })
                    }
                })
                .setMargin(60)
                .setOutCancel(true)
                .show(it)
        }
    }
}
