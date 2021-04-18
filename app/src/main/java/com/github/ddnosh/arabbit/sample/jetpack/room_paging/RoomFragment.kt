package com.github.ddnosh.arabbit.sample.jetpack.room_paging

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_room.*

class RoomFragment : BaseFragment() {

    private val userViewModel: UserAndroidViewModel by viewModels()

    override val contentViewLayoutID: Int = R.layout.fragment_room

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        add.setOnClickListener {
            userViewModel.add()
        }

        val adapter = UserAdapter()
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.adapter = adapter

        // 监听users数据，数据改变调用submitList方法
        userViewModel.users.observe(this, Observer(adapter::submitList))
    }
}