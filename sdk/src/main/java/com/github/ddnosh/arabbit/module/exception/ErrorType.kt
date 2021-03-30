package com.github.ddnosh.arabbit.module.exception

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
interface ErrorType {
    companion object {
        /**
         * 正常
         */
        val SUCCESS = 0
        /**
         * 未知错误
         */
        val UNKNOWN = 1000
        /**
         * 解析错误
         */
        val PARSE_ERROR = 1001
        /**
         * 没有网络
         */
        val NO_NETWORK = 1002
        /**
         * 网络错误 请求超时等
         */
        val NETWORK_ERROR = 1003
        /**
         * 协议出错 404 500 等
         */
        val HTTP_ERROR = 1004

        val RUN_TIME = 1005
    }
}
