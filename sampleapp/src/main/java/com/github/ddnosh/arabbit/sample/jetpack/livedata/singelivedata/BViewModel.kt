package com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata

import com.github.ddnosh.arabbit.jetpack.livedata.UnPeekLiveData
import com.github.ddnosh.arabbit.jetpack.viewmodel.BaseViewModel
import javax.inject.Inject

class BViewModel @Inject constructor() : BaseViewModel() {

    var b = UnPeekLiveData<String>()

    init {
        b.value = "b"
    }
}
