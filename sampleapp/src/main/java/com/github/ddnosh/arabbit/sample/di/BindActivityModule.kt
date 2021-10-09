package com.github.ddnosh.arabbit.sample.di

import com.github.ddnosh.arabbit.sample.MainActivity
import com.github.ddnosh.arabbit.sample.jetpack.livedata.LiveDataActivity
import com.github.ddnosh.arabbit.sample.jetpack.viewmodel.LoginActivity
import com.github.ddnosh.arabbit.ui.base.FrameActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindActivityModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun bindLiveDataActivity(): LiveDataActivity

    @ContributesAndroidInjector
    abstract fun bindFrameActivity(): FrameActivity
}
