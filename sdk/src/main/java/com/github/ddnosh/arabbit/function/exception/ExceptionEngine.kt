package com.github.ddnosh.arabbit.function.exception

import com.github.ddnosh.arabbit.util.LogUtil
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import java.text.ParseException
import javax.net.ssl.SSLException

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object ExceptionEngine {

    fun handleException(throwable: Throwable?): AppException {
        LogUtil.e("e", throwable?.message + ", " + throwable)
        val ex: AppException
        throwable?.let {
            when (it) {
                is HttpException -> {
                    ex = AppException(it.code(), it) //http异常有code
                }
                is SocketTimeoutException -> {
                    ex = AppException(Error.TIMEOUT)
                }
                is ConnectException -> {
                    ex = AppException(Error.CONNECTION_ERROR)
                }
                is SSLException -> {
                    ex = AppException(Error.SSL_ERROR)
                }
                is UnknownHostException, is UnknownServiceException, is IOException -> {
                    ex = AppException(Error.NO_NETWORK)
                }
                is RuntimeException -> {
                    ex = AppException(Error.RUN_TIME)
                }
                is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                    ex = AppException(Error.PARSE_ERROR)
                }
                else -> {
                    ex = AppException(Error.UNKNOWN)
                }
            }
            return ex
        }
        ex = AppException(Error.UNKNOWN)
        return ex
    }
}
