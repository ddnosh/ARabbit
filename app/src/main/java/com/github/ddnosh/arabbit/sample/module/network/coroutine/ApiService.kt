package com.github.ddnosh.arabbit.sample.module.network.coroutine

import com.github.ddnosh.arabbit.sample.module.network.coroutine.data.ApiPagerResponse
import com.github.ddnosh.arabbit.sample.module.network.coroutine.data.ApiResponse
import com.github.ddnosh.arabbit.sample.module.network.coroutine.data.ArticleResponse
import com.github.ddnosh.arabbit.sample.module.network.coroutine.data.ClassifyResponse
import retrofit2.http.*

interface ApiService {

    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
    }

    /**
     * 项目分类标题
     */
    @GET("project/tree/json")
    suspend fun getProjectTitle(): ApiResponse<ArrayList<ClassifyResponse>>

    /**
     * 获取最新项目数据
     */
    @GET("article/listproject/{page}/json")
    suspend fun getProjectNewData(@Path("page") pageNo: Int): ApiResponse<ApiPagerResponse<ArrayList<ArticleResponse>>>
}