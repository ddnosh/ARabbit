package com.github.ddnosh.arabbit.sample.jetpack.room_paging

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentRoomBinding

class RoomFragment : BaseFragment() {

    private val userViewModel by viewModels<UserAndroidViewModel>()

    private val binding by binding<FragmentRoomBinding>()
    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        binding.add.setOnClickListener {
            userViewModel.add()
        }

        val adapter = UserAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        // 监听users数据，数据改变调用submitList方法
        userViewModel.users.observe(this, Observer(adapter::submitList))
    }
}
