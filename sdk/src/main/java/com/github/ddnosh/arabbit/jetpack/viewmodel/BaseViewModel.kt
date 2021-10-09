package com.github.ddnosh.arabbit.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import com.github.ddnosh.arabbit.jetpack.livedata.EventMutableLiveData

open class BaseViewModel : ViewModel() {

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }

    inner class UiLoadingChange {
        // 显示加载框
        val showDialog by lazy { EventMutableLiveData<String>() }
        // 隐藏
        val dismissDialog by lazy { EventMutableLiveData<Boolean>() }
    }
}
