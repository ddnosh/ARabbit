package com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata

import com.github.ddnosh.arabbit.jetpack.livedata.UnPeekLiveData
import com.github.ddnosh.arabbit.jetpack.viewmodel.BaseViewModel
import javax.inject.Inject

class AViewModel @Inject constructor() : BaseViewModel() {

    var a = UnPeekLiveData<String>()

    init {
        a.value = "a"
    }
}
