package com.github.ddnosh.arabbit

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.multidex.MultiDexApplication

open class BaseApplication : MultiDexApplication(), ViewModelStoreOwner {

    private lateinit var mAppViewModelStore: ViewModelStore

    private var viewModelFactory: ViewModelProvider.Factory? = null

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    override fun onCreate() {
        super.onCreate()
        mAppViewModelStore = ViewModelStore()
    }

    // application's viewModel
    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, getFactory())
    }

    private fun getFactory(): ViewModelProvider.Factory {
        if (viewModelFactory == null) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return viewModelFactory as ViewModelProvider.Factory
    }
}
