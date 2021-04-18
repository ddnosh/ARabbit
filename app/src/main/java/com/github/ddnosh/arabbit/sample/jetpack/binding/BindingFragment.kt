package com.github.ddnosh.arabbit.sample.jetpack.binding

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.jetpack.binding.databinding.ViewDataBindingActivity
import com.github.ddnosh.arabbit.sample.jetpack.binding.viewbinding.ViewBindingActivity
import com.github.ddnosh.arabbit.sample.jetpack.livedata.LiveDataActivity
import com.github.ddnosh.arabbit.sample.jetpack.viewmodel.LoginActivity
import com.github.ddnosh.arabbit.ui.adapter.CommonAdapter
import com.github.ddnosh.arabbit.ui.adapter.CommonViewHolder
import com.github.ddnosh.arabbit.ui.dialog.ViewHolder
import com.github.ddnosh.arabbit.ui.dialog.dialogactivity.BaseDialog
import com.github.ddnosh.arabbit.ui.dialog.dialogactivity.ADialog
import com.github.ddnosh.arabbit.ui.dialog.dialogfragment.BaseDialogFragment
import com.github.ddnosh.arabbit.ui.dialog.dialogfragment.FDialog
import com.github.ddnosh.arabbit.util.LogUtil
import kotlinx.android.synthetic.main.fragment_sample.*
import kotlinx.android.synthetic.main.fragment_tab.*
import kotlinx.android.synthetic.main.fragment_tab.recycler_view

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class BindingFragment : BaseFragment() {

    private var bindingNameList = arrayListOf("1.ViewBinding", "2.ViewDataBinding")

    override val contentViewLayoutID: Int = R.layout.fragment_sample

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        include_toolbar.visibility = View.GONE
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.adapter = object : CommonAdapter<String>(requireActivity(), R.layout.sample_item, bindingNameList) {
            override fun convert(holder: CommonViewHolder, name: String) {
                holder.setText(R.id.sample_text, name)
                holder.setOnClickListener(R.id.item_root) {
                    LogUtil.d(TAG, "onItemClick:$name")
                    when (name) {
                        "1.ViewBinding" -> readyGo(ViewBindingActivity::class.java)
                        "2.ViewDataBinding" -> readyGo(ViewDataBindingActivity::class.java)
                    }
                }
            }
        }
    }
}