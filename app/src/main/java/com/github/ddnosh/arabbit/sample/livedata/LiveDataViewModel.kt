package com.github.ddnosh.arabbit.sample.livedata

import androidx.lifecycle.ViewModel
import com.github.ddnosh.arabbit.mvvm.livedata.EventMutableLiveData

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class LiveDataViewModel : ViewModel() {
    private val repository: LiveDataRepository = LiveDataRepository()
    val liveData: EventMutableLiveData<String> = repository.liveData
    fun test() {
        repository.test()
    }
}