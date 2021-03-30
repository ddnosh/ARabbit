package com.github.ddnosh.arabbit.module.exception

import android.util.Log
import com.github.ddnosh.arabbit.module.exception.ApiException

import com.google.gson.JsonParseException

import org.json.JSONException

import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import java.text.ParseException

import retrofit2.HttpException

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object ExceptionEngine {

    //对应HTTP的状态码
    private val UNAUTHORIZED = 401
    private val FORBIDDEN = 403
    private val NOT_FOUND = 404
    private val REQUEST_TIMEOUT = 408
    private val INTERNAL_SERVER_ERROR = 500
    private val BAD_GATEWAY = 502
    private val SERVICE_UNAVAILABLE = 503
    private val GATEWAY_TIMEOUT = 504

    fun handleException(throwable: Throwable): ApiException {
        Log.e("e", throwable.message + throwable)
        val ex: ApiException
        if (throwable is HttpException) {             //HTTP错误
            ex = ApiException(throwable, ErrorType.HTTP_ERROR)
            when (throwable.code()) {
                UNAUTHORIZED -> ex.msg = "当前请求需要用户验证"
                FORBIDDEN -> ex.msg = "服务器已经理解请求，但是拒绝执行它"
                NOT_FOUND -> ex.msg = "服务器异常，请稍后再试"
                REQUEST_TIMEOUT -> ex.msg = "请求超时"
                GATEWAY_TIMEOUT -> ex.msg = "服务器没有从远端服务器得到及时的响应"
                INTERNAL_SERVER_ERROR -> ex.msg = "服务器内部错误"
                BAD_GATEWAY -> ex.msg = "服务器接收到远端服务器的错误响应"
                SERVICE_UNAVAILABLE -> ex.msg = "服务器维护或者过载，服务器当前无法处理请求"
                else -> ex.msg = "网络错误"  //其它均视为网络错误
            }
        } else if (throwable is ServerException) {    //服务器返回的错误
            ex = ApiException(throwable, throwable.code)
            ex.msg = throwable.message
        } else if (throwable is SocketTimeoutException) {
            ex = ApiException(throwable, ErrorType.NETWORK_ERROR)
            ex.msg = "服务器响应超时"
        } else if (throwable is ConnectException) {
            ex = ApiException(throwable, ErrorType.NETWORK_ERROR)
            ex.msg = "网络连接异常，请检查网络"
        } else if (throwable is UnknownHostException || throwable is UnknownServiceException || throwable is IOException) {
            ex = ApiException(throwable, ErrorType.NO_NETWORK)
            ex.msg = "设备当前未联网，请检查网络设置"
        } else if (throwable is RuntimeException) {
            ex = ApiException(throwable, ErrorType.RUN_TIME)
            ex.msg = "很抱歉,程序运行出现了错误"
        } else if (throwable is JsonParseException
            || throwable is JSONException
            || throwable is ParseException) {
            ex = ApiException(throwable, ErrorType.PARSE_ERROR)
            ex.msg = "解析错误"
        } else {
            ex = ApiException(throwable, ErrorType.UNKNOWN)
            ex.msg = "未知错误"
        }
        return ex
    }
}
