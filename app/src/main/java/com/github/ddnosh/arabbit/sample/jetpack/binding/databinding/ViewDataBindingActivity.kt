package com.github.ddnosh.arabbit.sample.jetpack.binding.databinding

import android.os.Bundle
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseDbActivity
import com.github.ddnosh.arabbit.sample.databinding.ActivityDatabindingBinding


class ViewDataBindingActivity: BaseDbActivity<ActivityDatabindingBinding>() {
    override val contentViewLayoutID: Int = R.layout.activity_databinding

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        setTitle("DataBinding")
        val user = User("ddnosh", "male")
        binding.user = user
    }
}