package com.github.ddnosh.arabbit.sample.jetpack.binding.databinding

import android.os.Bundle
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.databinding.ActivityDatabindingBinding
import com.github.ddnosh.arabbit.ui.base.QuickDbActivity

class ViewDataBindingActivity : QuickDbActivity<ActivityDatabindingBinding>() {
    override val contentViewLayoutID: Int = R.layout.activity_databinding

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        title = "DataBinding"
        val user = User("ddnosh", "male")
        binding.user = user
    }
}
