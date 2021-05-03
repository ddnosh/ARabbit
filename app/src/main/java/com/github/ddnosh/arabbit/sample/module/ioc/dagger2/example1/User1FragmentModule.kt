package com.github.ddnosh.arabbit.sample.module.ioc.dagger2.example1

import com.github.ddnosh.arabbit.sample.module.ioc.dagger2.User
import dagger.Module
import dagger.Provides

@Module
class User1FragmentModule(private var user1Fragment: User1Fragment) {
    @Provides
    fun provideStudent() = User()
}