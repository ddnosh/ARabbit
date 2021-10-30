package com.github.ddnosh.arabbit.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import com.github.ddnosh.arabbit.jetpack.livedata.EventLiveData

open class BaseViewModel : ViewModel() {

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }

    inner class UiLoadingChange {
        // 显示加载框
        val showDialog by lazy { EventLiveData<String>() }
        // 隐藏
        val dismissDialog by lazy { EventLiveData<Boolean>() }
    }
}
