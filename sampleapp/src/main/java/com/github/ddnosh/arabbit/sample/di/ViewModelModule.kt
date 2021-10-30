package com.github.ddnosh.arabbit.sample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.ddnosh.arabbit.sample.jetpack.livedata.LiveDataViewModel
import com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata.AViewModel
import com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata.BViewModel
import com.github.ddnosh.arabbit.sample.jetpack.room_paging.UserAndroidViewModel
import com.github.ddnosh.arabbit.sample.module.ioc.dagger2.Dagger2ViewModel
import com.github.ddnosh.arabbit.sample.module.network.coroutine.viewmodel.RequestProjectViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(Dagger2ViewModel::class)
    abstract fun bindLiveDagger2ViewModel(dagger2ViewModel: Dagger2ViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LiveDataViewModel::class)
    abstract fun bindLiveDataViewModel(liveDataViewModel: LiveDataViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserAndroidViewModel::class)
    abstract fun bindUserAndroidViewModel(userAndroidViewModel: UserAndroidViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RequestProjectViewModel::class)
    abstract fun bindRequestProjectViewModel(requestProjectViewModel: RequestProjectViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AViewModel::class)
    abstract fun bindAViewModel(aViewModel: AViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BViewModel::class)
    abstract fun bindBViewModel(bViewModel: BViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
