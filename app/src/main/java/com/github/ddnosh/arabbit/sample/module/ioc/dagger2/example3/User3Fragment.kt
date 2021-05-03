package com.github.ddnosh.arabbit.sample.module.ioc.dagger2.example3

import android.content.SharedPreferences
import android.os.Bundle
import com.github.ddnosh.arabbit.sample.MyApplication
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.module.ioc.dagger2.User
import com.github.ddnosh.arabbit.util.LogUtil
import javax.inject.Inject

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class User3Fragment : BaseFragment() {

    @Inject
    lateinit var user: User

    @Inject
    lateinit var sp: SharedPreferences

    override val contentViewLayoutID: Int = R.layout.fragment_dagger

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {

    }
}