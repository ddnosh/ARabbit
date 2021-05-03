package com.github.ddnosh.arabbit.sample.module.ioc.dagger2.example2

import dagger.Component

@Component(modules = [User2FragmentModule::class], dependencies = [AppComponent::class])
interface User2FragmentComponent {

    fun inject(user2Fragment: User2Fragment)
}