package com.github.ddnosh.arabbit.sample.module.network.coroutine.data

import com.github.ddnosh.arabbit.module.network.BaseResponse

data class ApiResponse<T>(val errorCode: Int, val errorMsg: String, val data: T) : BaseResponse<T>() {

    override fun isSuccess() = errorCode == 0

    override fun getResponseCode() = errorCode

    override fun getResponseData() = data

    override fun getResponseMsg() = errorMsg

}