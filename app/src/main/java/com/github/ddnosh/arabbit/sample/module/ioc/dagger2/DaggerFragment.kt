package com.github.ddnosh.arabbit.sample.module.ioc.dagger2

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.module.ioc.dagger2.example1.User1Fragment
import com.github.ddnosh.arabbit.sample.module.ioc.dagger2.example2.User2Fragment
import com.github.ddnosh.arabbit.sample.module.ioc.dagger2.example3.User3Fragment
import com.github.ddnosh.arabbit.ui.adapter.CommonAdapter
import com.github.ddnosh.arabbit.ui.adapter.CommonViewHolder
import com.github.ddnosh.arabbit.util.LogUtil
import kotlinx.android.synthetic.main.fragment_sample.*
import kotlinx.android.synthetic.main.fragment_sample.recycler_view

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class DaggerFragment : BaseFragment() {

    private var bindingNameList = arrayListOf("1.Example1", "2.Example2", "3.Example3")

    override val contentViewLayoutID: Int = R.layout.fragment_sample

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        include_toolbar.visibility = View.GONE
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.adapter = object : CommonAdapter<String>(requireActivity(), R.layout.sample_item, bindingNameList) {
            override fun convert(holder: CommonViewHolder, t: String) {
                holder.setText(R.id.sample_text, t)
                holder.setOnClickListener(R.id.item_root) {
                    LogUtil.d(TAG, "onItemClick:$t")
                    when (t) {
                        "1.Example1" -> readyGo(User1Fragment::class.java)
                        "2.Example2" -> readyGo(User2Fragment::class.java)
                        "3.Example3" -> readyGo(User3Fragment::class.java)
                    }
                }
            }
        }
    }
}