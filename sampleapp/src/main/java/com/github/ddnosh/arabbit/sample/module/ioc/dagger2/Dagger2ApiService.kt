package com.github.ddnosh.arabbit.sample.module.ioc.dagger2

import com.github.ddnosh.arabbit.sample.module.network.coroutine.data.ApiResponse
import com.github.ddnosh.arabbit.sample.module.network.coroutine.data.ClassifyResponse
import retrofit2.http.GET

interface Dagger2ApiService {

    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
    }

    /**
     * 项目分类标题
     */
    @GET("project/tree/json")
    suspend fun getProjectTitle(): ApiResponse<ArrayList<ClassifyResponse>>
}
