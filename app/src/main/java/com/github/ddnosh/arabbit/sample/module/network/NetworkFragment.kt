package com.github.ddnosh.arabbit.sample.module.network

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.module.network.coroutine.NetworkFragment1
import com.github.ddnosh.arabbit.sample.module.network.rxjava.NetworkFragment2
import com.github.ddnosh.arabbit.ui.adapter.CommonAdapter
import com.github.ddnosh.arabbit.ui.adapter.CommonViewHolder
import com.github.ddnosh.arabbit.util.LogUtil
import kotlinx.android.synthetic.main.fragment_sample.*
import kotlinx.android.synthetic.main.fragment_tab.recycler_view

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class NetworkFragment : BaseFragment() {

    private var bindingNameList = arrayListOf("1.Coroutine Way", "2.RxJava Way")

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
                        "1.Coroutine Way" -> readyGo(NetworkFragment1::class.java)
                        "2.RxJava Way" -> readyGo(NetworkFragment2::class.java)
                    }
                }
            }
        }
    }
}