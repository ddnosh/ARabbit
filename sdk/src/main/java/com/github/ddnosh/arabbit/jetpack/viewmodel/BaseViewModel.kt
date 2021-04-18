package com.github.ddnosh.arabbit.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import com.github.ddnosh.arabbit.jetpack.livedata.EventMutableLiveData

open class BaseViewModel : ViewModel() {

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }

    /**
     * 内置封装好的可通知Activity/fragment 显示隐藏加载框 因为需要跟网络请求显示隐藏loading配套才加的，不然我加他个鸡儿加
     */
    inner class UiLoadingChange {
        //显示加载框
        val showDialog by lazy { EventMutableLiveData<String>() }
        //隐藏
        val dismissDialog by lazy { EventMutableLiveData<Boolean>() }
    }

}