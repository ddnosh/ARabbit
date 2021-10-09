package com.github.ddnosh.arabbit.sample.di

import android.content.Context
import android.content.SharedPreferences
import com.github.ddnosh.arabbit.sample.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: MyApplication): Context {
        return application
    }

    @Provides
    fun provideSharedPreferences(application: MyApplication): SharedPreferences {
        return application.getSharedPreferences("sp_default", Context.MODE_PRIVATE)
    }
}
