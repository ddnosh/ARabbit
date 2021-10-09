package com.github.ddnosh.arabbit.sample.module.network

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentSampleNoTitleBinding
import com.github.ddnosh.arabbit.sample.module.network.coroutine.Network1Fragment
import com.github.ddnosh.arabbit.sample.module.network.rxjava.Network2Fragment
import com.github.ddnosh.arabbit.ui.adapter.CommonAdapter
import com.github.ddnosh.arabbit.ui.adapter.CommonViewHolder
import com.github.ddnosh.arabbit.util.LogUtil

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class NetworkFragment : BaseFragment() {

    private var bindingNameList = arrayListOf("1.Coroutine Way", "2.RxJava Way")

    private val binding by binding<FragmentSampleNoTitleBinding>()
    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = object : CommonAdapter<String>(requireActivity(), R.layout.sample_item, bindingNameList) {
            override fun convert(holder: CommonViewHolder, name: String) {
                holder.setText(R.id.sample_text, name)
                holder.setOnClickListener(R.id.item_root) {
                    LogUtil.d(TAG, "onItemClick:$name")
                    when (name) {
                        "1.Coroutine Way" -> readyGo(Network1Fragment::class.java)
                        "2.RxJava Way" -> readyGo(Network2Fragment::class.java)
                    }
                }
            }
        }
    }
}
