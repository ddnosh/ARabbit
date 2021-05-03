package com.github.ddnosh.arabbit.sample.module.ioc.dagger2.example2

import android.content.SharedPreferences
import com.github.ddnosh.arabbit.sample.MyApplication
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun sharedPreferences(): SharedPreferences

    fun myApplication(): MyApplication
}