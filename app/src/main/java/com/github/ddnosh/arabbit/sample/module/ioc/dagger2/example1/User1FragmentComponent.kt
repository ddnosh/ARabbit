package com.github.ddnosh.arabbit.sample.module.ioc.dagger2.example1

import dagger.Component

@Component(modules = [User1FragmentModule::class])
interface User1FragmentComponent {
    fun inject(user1Fragment: User1Fragment)
}