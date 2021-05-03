package com.github.ddnosh.arabbit.sample.module.ioc.dagger2.example1

import android.os.Bundle
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.module.ioc.dagger2.User
import javax.inject.Inject

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class User1Fragment : BaseFragment() {

    @Inject
    lateinit var user: User

    override val contentViewLayoutID: Int = R.layout.fragment_dagger

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        DaggerUser1FragmentComponent.builder()
                .user1FragmentModule(User1FragmentModule(this))
                .build()
                .inject(this)

        println(user.study())
    }
}