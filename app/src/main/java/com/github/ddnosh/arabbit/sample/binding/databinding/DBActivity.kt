package com.github.ddnosh.arabbit.sample.binding.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.databinding.ActivityDbBinding


class DBActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMain2Binding: ActivityDbBinding = DataBindingUtil.setContentView(this, R.layout.activity_db)
        val user = User("ddnosh", "male")
        activityMain2Binding.user = user
    }
}