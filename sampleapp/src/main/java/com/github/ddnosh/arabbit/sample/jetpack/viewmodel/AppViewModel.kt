package com.github.ddnosh.arabbit.sample.jetpack.viewmodel

import com.github.ddnosh.arabbit.jetpack.livedata.EventLiveData
import com.github.ddnosh.arabbit.jetpack.livedata.UnPeekLiveData
import com.github.ddnosh.arabbit.jetpack.viewmodel.BaseViewModel
import com.github.ddnosh.arabbit.module.network.model.NetworkStatus

// 全局ViewModel, 存放全局数据
class AppViewModel : BaseViewModel() {

    var info = UnPeekLiveData<String>()

    val networkStatus = EventLiveData<NetworkStatus>()

    init {
        info.value = "arrabStatusit"
    }
}
