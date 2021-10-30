package com.github.ddnosh.arabbit.sample.module.network.coroutine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.ddnosh.arabbit.ext.request
import com.github.ddnosh.arabbit.jetpack.viewmodel.BaseViewModel
import com.github.ddnosh.arabbit.module.network.state.ResultState
import com.github.ddnosh.arabbit.sample.module.network.coroutine.apiService
import com.github.ddnosh.arabbit.sample.module.network.coroutine.data.ArticleResponse
import com.github.ddnosh.arabbit.sample.module.network.coroutine.data.ClassifyResponse
import com.github.ddnosh.arabbit.sample.module.network.coroutine.state.ListDataUiState
import javax.inject.Inject

class RequestProjectViewModel @Inject constructor() : BaseViewModel() {

    private var pageNo = 1

    var titleData = MutableLiveData<ResultState<ArrayList<ClassifyResponse>>>()

    private var projectDataState = MutableLiveData<ListDataUiState<ArticleResponse>>()

    fun getProjectTitleData() {
        request({
            apiService.getProjectTitle()
        }, titleData, true)
    }

    fun getProjectData(isRefresh: Boolean, cid: Int, isNew: Boolean = false) {
        if (isRefresh) {
            pageNo = if (isNew) 0 else 1
        }
        request({ apiService.getProjectNewData(pageNo) }, {
            // 请求成功
            pageNo++
            val listDataUiState =
                ListDataUiState(
                    isSuccess = true,
                    isRefresh = isRefresh,
                    isEmpty = it.isEmpty(),
                    hasMore = it.hasMore(),
                    isFirstEmpty = isRefresh && it.isEmpty(),
                    listData = it.datas
                )
            projectDataState.value = listDataUiState
        }, {
            // 请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMessage,
                    isRefresh = isRefresh,
                    listData = arrayListOf<ArticleResponse>()
                )
            projectDataState.value = listDataUiState
        })
    }
}
