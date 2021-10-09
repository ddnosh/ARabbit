package com.github.ddnosh.arabbit.sample.module.ioc.dagger2

import androidx.lifecycle.MutableLiveData
import com.github.ddnosh.arabbit.ext.request
import com.github.ddnosh.arabbit.jetpack.viewmodel.BaseViewModel
import com.github.ddnosh.arabbit.module.network.state.ResultState
import com.github.ddnosh.arabbit.sample.module.network.coroutine.data.ClassifyResponse
import javax.inject.Inject

class Dagger2ViewModel @Inject constructor(private val dagger2ApiService: Dagger2ApiService) : BaseViewModel() {

    fun getProjectTitleData(): MutableLiveData<ResultState<ArrayList<ClassifyResponse>>> {
        val titleData = MutableLiveData<ResultState<ArrayList<ClassifyResponse>>>()
        request({
            dagger2ApiService.getProjectTitle()
        }, titleData, true)
        return titleData
    }
}
