package com.github.ddnosh.arabbit.module.exception

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class ServerException(// 异常处理，为速度，不必要设置getter和setter
    var code: Int, override var message: String
) : RuntimeException(message)

