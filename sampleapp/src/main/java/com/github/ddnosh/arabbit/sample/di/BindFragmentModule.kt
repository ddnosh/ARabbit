package com.github.ddnosh.arabbit.sample.di

import com.github.ddnosh.arabbit.sample.TabFragment
import com.github.ddnosh.arabbit.sample.function.error.ExceptionFragment
import com.github.ddnosh.arabbit.sample.jetpack.binding.BindingFragment
import com.github.ddnosh.arabbit.sample.jetpack.coroutine.CoroutineFragment
import com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata.AFragment
import com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata.BFragment
import com.github.ddnosh.arabbit.sample.jetpack.navigation.NavigationFragment
import com.github.ddnosh.arabbit.sample.jetpack.navigation.OneFragment
import com.github.ddnosh.arabbit.sample.jetpack.navigation.TwoFragment
import com.github.ddnosh.arabbit.sample.jetpack.room_paging.RoomFragment
import com.github.ddnosh.arabbit.sample.module.communication.rxbus.RxBusFragment
import com.github.ddnosh.arabbit.sample.module.image.GlideFragment
import com.github.ddnosh.arabbit.sample.module.ioc.dagger2.Dagger2Fragment
import com.github.ddnosh.arabbit.sample.module.network.NetworkFragment
import com.github.ddnosh.arabbit.sample.module.network.coroutine.Network1Fragment
import com.github.ddnosh.arabbit.sample.module.network.rxjava.Network2Fragment
import com.github.ddnosh.arabbit.sample.ui.multiplestatusview.MultipleViewStateFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [BindBaseModule::class])
abstract class BindFragmentModule {
    @ContributesAndroidInjector
    abstract fun bindDagger2Fragment(): Dagger2Fragment

    @ContributesAndroidInjector
    abstract fun bindTabFragment(): TabFragment

    @ContributesAndroidInjector
    abstract fun bindCoroutineFragment(): CoroutineFragment

    @ContributesAndroidInjector
    abstract fun bindBindingFragment(): BindingFragment

    @ContributesAndroidInjector
    abstract fun bindNavigationFragment(): NavigationFragment

    @ContributesAndroidInjector
    abstract fun bindRoomFragment(): RoomFragment

    @ContributesAndroidInjector
    abstract fun bindOneFragment(): OneFragment

    @ContributesAndroidInjector
    abstract fun bindTwoFragment(): TwoFragment

    @ContributesAndroidInjector
    abstract fun bindRxBusFragment(): RxBusFragment

    @ContributesAndroidInjector
    abstract fun bindGlideFragment(): GlideFragment

    @ContributesAndroidInjector
    abstract fun bindExceptionFragment(): ExceptionFragment

    @ContributesAndroidInjector
    abstract fun bindAFragment(): AFragment

    @ContributesAndroidInjector
    abstract fun bindBFragment(): BFragment

    @ContributesAndroidInjector
    abstract fun bindMultipleViewStateFragment(): MultipleViewStateFragment

    @ContributesAndroidInjector
    abstract fun bindNetworkFragment(): NetworkFragment

    @ContributesAndroidInjector
    abstract fun bindNetwork1Fragment(): Network1Fragment

    @ContributesAndroidInjector
    abstract fun bindNetwork2Fragment(): Network2Fragment
}
