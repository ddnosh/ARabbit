package com.github.ddnosh.arabbit.sample.jetpack.viewmodel

import com.github.ddnosh.arabbit.jetpack.livedata.UnPeekLiveData
import com.github.ddnosh.arabbit.jetpack.viewmodel.BaseViewModel

// 全局ViewModel, 存放全局数据
class AppViewModel : BaseViewModel() {

    var info = UnPeekLiveData<String>()

    init {
        info.value = "arrabit"
    }
}
