package com.github.ddnosh.arabbit.module.exception

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class ApiException(throwable: Throwable, var code: Int) : Exception(throwable) {
    var msg: String? = null
}
