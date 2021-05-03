package com.github.ddnosh.arabbit.sample.module.ioc.dagger2.example2

import com.github.ddnosh.arabbit.sample.module.ioc.dagger2.User
import dagger.Module
import dagger.Provides

@Module
class User2FragmentModule(private var user2Fragment: User2Fragment) {
    @Provides
    fun provideStudent() = User()
}