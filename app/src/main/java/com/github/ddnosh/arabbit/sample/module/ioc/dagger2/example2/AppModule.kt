package com.github.ddnosh.arabbit.sample.module.ioc.dagger2.example2

import android.content.Context
import android.content.SharedPreferences
import com.github.ddnosh.arabbit.sample.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Inject


@Module
class AppModule @Inject constructor(private val application: MyApplication) {

    //提供全局的sp对象
    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences("sp_default", Context.MODE_PRIVATE)
    }

    //提供全局的Application对象
    @Provides
    fun provideApplication(): MyApplication {
        return application
    }
}