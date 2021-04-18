package com.github.ddnosh.arabbit.function.exception

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class AppException() : Exception() {
    var errorCode = 0
    lateinit var errorMessage: String

    constructor(errorCode: Int, throwable: Throwable) : this() {
        this.errorCode = errorCode
        this.errorMessage = throwable.message ?: "请求失败，请稍后再试"
    }

    constructor(errorCode: Int, errorMessage: String?) : this() {
        this.errorCode = errorCode
        this.errorMessage = errorMessage ?: "请求失败，请稍后再试"
    }

    constructor(error: Error) : this() {
        errorCode = error.getKey()
        errorMessage = error.getValue()
    }
}
